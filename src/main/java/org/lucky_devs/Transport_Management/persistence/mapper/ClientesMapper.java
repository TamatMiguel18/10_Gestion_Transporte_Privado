package org.lucky_devs.Transport_Management.persistence.mapper;

import org.lucky_devs.Transport_Management.dominio.dto.ClientesDto;
import org.lucky_devs.Transport_Management.persistence.entity.ClientesEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientesMapper {

    @Mapping(source = "id_cliente", target = "id_cliente")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "correo", target = "correo")
    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "edad", target = "edad")
    @Mapping(source = "genero", target = "genero")
    ClientesEntity toEntity(ClientesDto dto);

    @Mapping(source = "id_cliente", target = "id_cliente")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "correo", target = "correo")
    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "edad", target = "edad")
    @Mapping(source = "genero", target = "genero")
    ClientesDto toDto(ClientesEntity entity);

    List<ClientesDto> toDtoList(Iterable<ClientesEntity> entities);

    void updateEntityFromDto(ClientesDto dto, @MappingTarget ClientesEntity entity);

    List<ClientesDto> toDto(List<ClientesEntity> all);
}
