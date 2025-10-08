package org.lucky_devs.Transport_Management.dominio.service;

import lombok.RequiredArgsConstructor;
import org.lucky_devs.Transport_Management.dominio.dto.ModReservaDto;
import org.lucky_devs.Transport_Management.dominio.dto.ReservaRequestDto;
import org.lucky_devs.Transport_Management.dominio.dto.ReservaResponseDto;
import org.lucky_devs.Transport_Management.dominio.exception.ReservaNotFound;
import org.lucky_devs.Transport_Management.persistence.entity.ClienteEntity;
import org.lucky_devs.Transport_Management.persistence.entity.ReservaEntity;
import org.lucky_devs.Transport_Management.persistence.entity.RutaEntity;
import org.lucky_devs.Transport_Management.persistence.entity.TarifasEntity;
import org.lucky_devs.Transport_Management.persistence.mapper.ReservaMapper;
import org.lucky_devs.Transport_Management.repository.ClientesRepository;
import org.lucky_devs.Transport_Management.repository.ReservaRepository;
import org.lucky_devs.Transport_Management.repository.RutasRepository;
import org.lucky_devs.Transport_Management.repository.TarifasRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final ReservaMapper reservaMapper;
    private final TarifasRepository tarifaRepository;
    private final ClientesRepository clienteRepository;
    private final RutasRepository rutaRepository;

    private static final LocalTime PICO_MANANA_INICIO = LocalTime.of(7, 0);
    private static final LocalTime PICO_MANANA_FIN = LocalTime.of(9, 0);
    private static final LocalTime PICO_TARDE_INICIO = LocalTime.of(17, 0);
    private static final LocalTime PICO_TARDE_FIN = LocalTime.of(19, 0);
    private static final double RECARGO_PICO = 0.25;


    private BigDecimal calcularPrecioFinal(LocalDateTime fechaHoraInicio, BigDecimal precioBase) {
        LocalTime horaInicio = fechaHoraInicio.toLocalTime();
        // Lógica de hora pico...
        boolean esPicoManana = (horaInicio.isAfter(PICO_MANANA_INICIO) || horaInicio.equals(PICO_MANANA_INICIO)) && horaInicio.isBefore(PICO_MANANA_FIN);
        boolean esPicoTarde = (horaInicio.isAfter(PICO_TARDE_INICIO) || horaInicio.equals(PICO_TARDE_INICIO)) && horaInicio.isBefore(PICO_TARDE_FIN);

        if (esPicoManana || esPicoTarde) {
            BigDecimal recargo = precioBase.multiply(new BigDecimal(RECARGO_PICO));
            return precioBase.add(recargo);
        }

        return precioBase;
    }

    public List<ReservaResponseDto> listaReservas() {
        return this.reservaMapper.toDto(this.reservaRepository.findAll());
    }

    public ReservaResponseDto buscarReservaPorId(Long id_reserva) {
        ReservaEntity reservaEntity = this.reservaRepository.findById(id_reserva)
                .orElseThrow(() -> new ReservaNotFound(id_reserva));

        return this.reservaMapper.toDto(reservaEntity);
    }

    public ReservaResponseDto agregarNuevaReserva(ReservaRequestDto requestDto) {
        TarifasEntity tarifaEntity = tarifaRepository.findById(requestDto.idTarifa())
                .orElseThrow(() -> new RuntimeException("Tarifa no encontrada con ID: " + requestDto.idTarifa()));

        ClienteEntity clienteEntity = clienteRepository.findById(requestDto.idCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + requestDto.idCliente()));

        RutaEntity rutaEntity = rutaRepository.findById(requestDto.idRuta())
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada con ID: " + requestDto.idRuta()));

        BigDecimal precioBase = new BigDecimal(tarifaEntity.getTarifa_base());
        BigDecimal precioFinal = calcularPrecioFinal(requestDto.fechaHoraInicio(), precioBase);

        ReservaEntity reservaEntity = this.reservaMapper.toEntity(requestDto);

        reservaEntity.setTarifas(tarifaEntity);
        reservaEntity.setClientes(clienteEntity);
        reservaEntity.setRutas(rutaEntity);

        reservaEntity.setPrecioFinal(precioFinal);
        this.reservaRepository.save(reservaEntity);
        return this.reservaMapper.toDto(reservaEntity);
    }
    public ReservaResponseDto modificarReservaExistente(Long id, ModReservaDto modReservaDto) {
        ReservaEntity existingReserva = this.reservaRepository.findById(id)
                .orElseThrow(() -> new ReservaNotFound(id));

        if (!existingReserva.getTarifas().getId_tarifa().equals(modReservaDto.idTarifa()) ||
                !existingReserva.getFechaHoraInicio().equals(modReservaDto.fechaHoraInicio())) {

            TarifasEntity tarifaEntity = tarifaRepository.findById(modReservaDto.idTarifa())
                    .orElseThrow(() -> new RuntimeException("Tarifa no encontrada con ID: " + modReservaDto.idTarifa())); // CORRECCIÓN

            BigDecimal precioBase = new BigDecimal(tarifaEntity.getTarifa_base());
            BigDecimal nuevoPrecioFinal = calcularPrecioFinal(modReservaDto.fechaHoraInicio(), precioBase);

            existingReserva.setTarifas(tarifaEntity); // Actualizar la entidad de Tarifa
            existingReserva.setPrecioFinal(nuevoPrecioFinal);
        }
        this.reservaMapper.modificarEntityFromDto(modReservaDto, existingReserva);

        return this.reservaMapper.toDto(this.reservaRepository.save(existingReserva));
    }

    public void eliminarReserva(Long id_reserva) {
        ReservaEntity reservaEntity = this.reservaRepository.findById(id_reserva)
                .orElseThrow(() -> new ReservaNotFound(id_reserva));

        this.reservaRepository.delete(reservaEntity);
    }
}