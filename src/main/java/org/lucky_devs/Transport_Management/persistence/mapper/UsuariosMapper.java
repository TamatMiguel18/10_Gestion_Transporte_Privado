package org.lucky_devs.Transport_Management.persistence.mapper;

import org.lucky_devs.Transport_Management.dominio.dto.UsuarioDto;
import org.lucky_devs.Transport_Management.persistence.entity.ConductorEntity;
import org.lucky_devs.Transport_Management.persistence.entity.UsuariosEntity;
import org.mapstruct.*;

import java.util.List;
@Mapper(componentModel = "spring")
public interface UsuariosMapper {

    @Mapping(source = "id_usuario", target = "id_usuario")
    @Mapping(source = "id_conductor", target = "conductor")
    @Mapping(source = "correo", target = "correo")
    @Mapping(source = "contrasena", target = "contrasena")
    UsuariosEntity toEntity(UsuarioDto dto);

    @Mapping(source = "id_usuario", target = "id_usuario")
    @Mapping(source = "conductor.id_conductor", target = "id_conductor")
    @Mapping(source = "correo", target = "correo")
    @Mapping(source = "contrasena", target = "contrasena")
    UsuarioDto toDto(UsuariosEntity entity);

    List<UsuarioDto> toDtoList(Iterable<UsuariosEntity> entities);

    void updateEntityFromDto(UsuarioDto dto, @MappingTarget UsuariosEntity entity);

    List<UsuarioDto> toDto(List<UsuariosEntity> all);

    // Método auxiliar para crear un ConductorEntity a partir de un ID
    default ConductorEntity map(Long id) {
        if (id == null) {
            return null;
        }
        ConductorEntity conductor = new ConductorEntity();
        conductor.setId_conductor(id);
        return conductor;
    }

    // Método auxiliar para obtener el ID desde ConductorEntity
    default Long map(ConductorEntity conductor) {
        if (conductor == null) {
            return null;
        }
        return conductor.getId_conductor();
    }
}