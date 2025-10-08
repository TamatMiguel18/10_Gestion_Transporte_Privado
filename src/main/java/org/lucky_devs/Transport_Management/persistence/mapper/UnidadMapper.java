package org.lucky_devs.Transport_Management.persistence.mapper;

import org.lucky_devs.Transport_Management.dominio.dto.ModUnidadDto;
import org.lucky_devs.Transport_Management.dominio.dto.UnidadRequestDto;
import org.lucky_devs.Transport_Management.dominio.dto.UnidadResponseDto;
import org.lucky_devs.Transport_Management.persistence.entity.UnidadEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UnidadMapper {
    @Mapping(source = "id_unidad", target = "id_unidad")
    @Mapping(source = "placa", target = "placa")
    @Mapping(source = "modelo", target = "modelo")
    @Mapping(source = "capacidad", target = "capacidad")
    @Mapping(source = "tipo_unidad", target = "tipo_unidad")
    @Mapping(source = "estado_operativo", target = "estado_operativo")
    UnidadResponseDto toDto(UnidadEntity unidadEntity);

    @Mapping(source = "placa", target = "placa")
    @Mapping(source = "modelo", target = "modelo")
    @Mapping(source = "capacidad", target = "capacidad")
    @Mapping(source = "tipo_unidad", target = "tipo_unidad")
    UnidadEntity toEntity(UnidadRequestDto unidadRequestDto);
    List<UnidadResponseDto> toDto(Iterable<UnidadEntity> unidadEntities);
    void modificarEntityFromDto(ModUnidadDto modUnidadDto, @MappingTarget UnidadEntity unidadEntity);
}
