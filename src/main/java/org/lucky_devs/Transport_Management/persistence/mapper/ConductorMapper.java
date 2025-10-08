package org.lucky_devs.Transport_Management.persistence.mapper;

import org.lucky_devs.Transport_Management.dominio.dto.ConductorDto;
import org.lucky_devs.Transport_Management.dominio.dto.ModConductor;
import org.lucky_devs.Transport_Management.persistence.entity.ConductorEntity;
import org.mapstruct.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ConductorMapper {


    @Mapping(source = "id_conductor", target = "id_conductor")
    @Mapping(source = "fecha_nacimiento", target = "fechaNacimiento", qualifiedByName = "dateToLocalDate")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "licencia", target = "licencia")
    @Mapping(source = "estado", target = "estado")
    @Mapping(source = "direccion", target = "direccion")
    @Mapping(source = "telefono", target = "telefono")
    ConductorDto toDto(ConductorEntity conductorEntity);


    @Mapping(source = "id_conductor", target = "id_conductor")
    @Mapping(source = "fechaNacimiento", target = "fecha_nacimiento", qualifiedByName = "localDateToDate")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "licencia", target = "licencia")
    @Mapping(source = "estado", target = "estado")
    @Mapping(source = "direccion", target = "direccion")
    @Mapping(source = "telefono", target = "telefono")
    ConductorEntity toEntity(ConductorDto dto);


    List<ConductorDto> toDto(List<ConductorEntity> conductores);
    List<ConductorDto> toDto(Iterable<ConductorEntity> entities);

    void modificarEntityFromDto(ModConductor modConductorDto, @MappingTarget ConductorEntity entity);

    @Named("dateToLocalDate")
    default LocalDate dateToLocalDate(Date date) {
        if (date == null) return null;
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @Named("localDateToDate")
    default Date localDateToDate(LocalDate localDate) {
        if (localDate == null) return null;
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
