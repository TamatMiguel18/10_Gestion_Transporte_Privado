package org.lucky_devs.Transport_Management.persistence.mapper;


import org.lucky_devs.Transport_Management.dominio.dto.ConductorDto;
import org.lucky_devs.Transport_Management.dominio.dto.ModConductor;
import org.lucky_devs.Transport_Management.persistence.entity.ConductorEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper (componentModel = "Spring")
public interface ConductorMapper {
    @Mapping(source = "id_Conductor", target = "id_conductor")
    @Mapping(source = "licencia", target = "licencia")
    @Mapping(source = "fechaNacimiento", target = "fecha_nacimineto")
    @Mapping(source = "estado", target = "estado")
    @Mapping(source = "antiguedad", target = "antiguedad")
    ConductorDto toDto (ConductorEntity entity);

    List<ConductorDto> toDto(Iterable<ConductorDto> entities);
    @Mapping(source = "id_conductor", target = "id_Conductor")
    @Mapping(source = "licencia", target = "licencia")
    @Mapping(source = "fecha_nacimineto", target = "fechaNacimiento")
    @Mapping(source = "estado", target = "estado")
    @Mapping(source = "antiguedad", target = "antiguedad")
    ConductorEntity toEntity(ConductorDto dto);

    void modificarEntityFromDto(ModConductor modDetalleRutinaDto, @MappingTarget ConductorEntity entity);


}