package org.lucky_devs.Transport_Management.persistence.mapper;

import org.lucky_devs.Transport_Management.dominio.dto.ModReservaDto;
import org.lucky_devs.Transport_Management.dominio.dto.ReservaRequestDto;
import org.lucky_devs.Transport_Management.dominio.dto.ReservaResponseDto;
import org.lucky_devs.Transport_Management.persistence.entity.*;
import org.lucky_devs.Transport_Management.repository.ClientesRepository;
import org.lucky_devs.Transport_Management.repository.RutasRepository;
import org.lucky_devs.Transport_Management.repository.TarifasRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;

import java.util.List;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ReservaMapper {
    @Autowired
    private TarifasRepository tarifasRepository;
    @Autowired
    private ClientesRepository clientesRepository;
    @Autowired
    private RutasRepository rutasRepository;

    @Mapping(source = "idReserva", target = "idReserva")
    @Mapping(source = "tarifas.id_tarifa", target = "idTarifa")
    @Mapping(source = "clientes.id_cliente", target = "idCliente")
    @Mapping(source = "rutas.id_ruta", target = "idRuta")
    @Mapping(source = "fechaHoraInicio", target = "fechaHoraInicio")
    @Mapping(source = "fechaHoraFin", target = "fechaHoraFin")
    @Mapping(source = "direccionOrigen", target = "direccionOrigen")
    @Mapping(source = "estadoViaje", target = "estadoViaje")
    @Mapping(source = "precioFinal", target = "precioFinal")
    public abstract ReservaResponseDto toDto(ReservaEntity entity);

    @Mapping(ignore = true, target = "idReserva")
    @Mapping(source = "idTarifa", target = "tarifas.id_tarifa")
    @Mapping(source = "idCliente", target = "clientes.id_cliente")
    @Mapping(source = "idRuta", target = "rutas.id_ruta")
    @Mapping(source = "fechaHoraInicio", target = "fechaHoraInicio")
    @Mapping(source = "fechaHoraFin", target = "fechaHoraFin")
    @Mapping(source = "direccionOrigen", target = "direccionOrigen")
    @Mapping(source = "estadoViaje", target = "estadoViaje")
    public abstract ReservaEntity toEntity(ReservaRequestDto requestDto);

    public abstract List<ReservaResponseDto> toDto(Iterable<ReservaEntity> entities);

    public abstract void modificarEntityFromDto(ModReservaDto modReservaDto, @MappingTarget ReservaEntity reservaEntity);

    public TarifasEntity mapIdTarifaToTarifa(Long idTarifa){
        if (idTarifa == null){
            return null;
        }else{
            return tarifasRepository.findById(idTarifa).orElseThrow(() -> new RuntimeException("Tarifa no encontrada"));
        }
    }

    public ClienteEntity mapIdClienteToCliente(Long idCliente){
        if (idCliente == null){
            return null;
        }else{
            return clientesRepository.findById(idCliente).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        }
    }

    public RutaEntity mapIdConductorToConductor(Long idRuta){
        if (idRuta == null){
            return null;
        }else{
            return rutasRepository.findById(idRuta).orElseThrow(() -> new RuntimeException("Ruta no encontrada"));
        }
    }
}
