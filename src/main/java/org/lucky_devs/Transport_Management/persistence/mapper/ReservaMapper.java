package org.lucky_devs.Transport_Management.persistence.mapper;

import org.lucky_devs.Transport_Management.dominio.dto.ModReservaDto;
import org.lucky_devs.Transport_Management.dominio.dto.ReservaRequestDto;
import org.lucky_devs.Transport_Management.dominio.dto.ReservaResponseDto;
import org.lucky_devs.Transport_Management.persistence.entity.ReservaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservaMapper {

    @Mapping(source = "idReserva", target = "idReserva")
    @Mapping(source = "direccionOrigen", target = "direccionOrigen")
    ReservaResponseDto toDto(ReservaEntity entity);

    @Mapping(target = "direccionOrigen", source = "direccionOrigen")
    ReservaEntity toEntity(ReservaRequestDto requestDto);

    List<ReservaResponseDto> toDto(Iterable<ReservaEntity> entities);

    void modificarEntityFromDto(ModReservaDto modReservaDto, @MappingTarget ReservaEntity reservaEntity);
}
