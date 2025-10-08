package org.lucky_devs.Transport_Management.dominio.service;

import lombok.RequiredArgsConstructor;
import org.lucky_devs.Transport_Management.dominio.dto.ModReservaDto;
import org.lucky_devs.Transport_Management.dominio.dto.ReservaRequestDto;
import org.lucky_devs.Transport_Management.dominio.dto.ReservaResponseDto;
import org.lucky_devs.Transport_Management.dominio.exception.ReservaNotFound;
import org.lucky_devs.Transport_Management.persistence.entity.ReservaEntity;
import org.lucky_devs.Transport_Management.persistence.mapper.ReservaMapper;
import org.lucky_devs.Transport_Management.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final ReservaMapper reservaMapper;
    private final TarifaRepository tarifaRepository;

    private static final LocalTime PICO_MANANA_INICIO = LocalTime.of(7, 0);
    private static final LocalTime PICO_MANANA_FIN = LocalTime.of(9, 0);
    private static final LocalTime PICO_TARDE_INICIO = LocalTime.of(17, 0);
    private static final LocalTime PICO_TARDE_FIN = LocalTime.of(19, 0);
    private static final double RECARGO_PICO = 0.25;

    public ReservaService(ReservaRepository reservaRepository, ReservaMapper reservaMapper, TarifaRepository tarifaRepository) {
        this.reservaRepository = reservaRepository;
        this.reservaMapper = reservaMapper;
        this.tarifaRepository = tarifaRepository;
    }

    private BigDecimal calcularPrecioFinal(LocalDateTime fechaHoraInicio, BigDecimal precioBase) {
        LocalTime horaInicio = fechaHoraInicio.toLocalTime();
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
        TarifaEntity tarifaEntity = tarifaRepository.findById(requestDto.idTarifa())
                .orElseThrow(() -> new TarifaNotFound("Tarifa con ID: " + requestDto.idTarifa() + " no encontrada."));
        BigDecimal precioFinal = calcularPrecioFinal(requestDto.fechaHoraInicio(), tarifaEntity.getPrecioBase());
        ReservaEntity reservaEntity = this.reservaMapper.toEntity(requestDto);
        reservaEntity.setPrecioFinal(precioFinal);
        this.reservaRepository.save(reservaEntity);
        return this.reservaMapper.toDto(reservaEntity);
    }

    public ReservaResponseDto modificarReservaExistente(Long id, ModReservaDto modReservaDto) {
        ReservaEntity existingReserva = this.reservaRepository.findById(id)
                .orElseThrow(() -> new ReservaNotFound(id));
        if (!existingReserva.getIdTarifa().equals(modReservaDto.idTarifa()) ||
                !existingReserva.getFechaHoraInicio().equals(modReservaDto.fechaHoraInicio())) {

            TarifaEntity tarifaEntity = tarifaRepository.findById(modReservaDto.idTarifa())
                    .orElseThrow(() -> new TarifaNotFound("Tarifa con ID: " + modReservaDto.idTarifa() + " no encontrada."));

            BigDecimal nuevoPrecioFinal = calcularPrecioFinal(modReservaDto.fechaHoraInicio(), tarifaEntity.getPrecioBase());
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
