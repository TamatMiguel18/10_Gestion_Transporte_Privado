package org.lucky_devs.Transport_Management.persistence.mapper;

import org.lucky_devs.Transport_Management.dominio.dto.RutasRequestDto;
import org.lucky_devs.Transport_Management.dominio.dto.RutasResponseDto;
import org.lucky_devs.Transport_Management.persistence.entity.ConductorEntity;
import org.lucky_devs.Transport_Management.persistence.entity.RutaEntity;
import org.lucky_devs.Transport_Management.persistence.entity.UnidadEntity;
import org.lucky_devs.Transport_Management.repository.ConductorRepository;
import org.lucky_devs.Transport_Management.repository.UnidadRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class RutasMapper {
    @Autowired
    private UnidadRepository unidadRepository;

    @Autowired
    private ConductorRepository conductorRepository;

    @Mapping(source = "id_ruta",target = "id_ruta")
    @Mapping(source = "conductor.id_conductor",target = "id_conductor")
    @Mapping(source = "unidad.id_unidad",target = "id_unidad")
    @Mapping(source = "direccion_destino",target = "direccion_destino")
    public abstract RutasResponseDto toDto(RutaEntity rutaEntity);

    public abstract List<RutasResponseDto> toDto(Iterable<RutaEntity> rutaEntities);

    @Mapping(target = "id_ruta", ignore = true)
    @Mapping(source = "id_conductor",target = "conductor.id_conductor")
    @Mapping(source = "id_unidad",target = "unidad.id_unidad")
    @Mapping(source = "direccion_destino",target = "direccion_destino")
    public abstract RutaEntity toEntity(RutasRequestDto rutasRequestDto);

    public abstract void modificarEntityFromDto(RutasRequestDto rutasRequestDto, @MappingTarget RutaEntity rutaEntity);

    public UnidadEntity mapIdUnidadToUnidad(Long id_unidad){
        if (id_unidad == null){
            return null;
        }else{
            return unidadRepository.findById(id_unidad).orElseThrow(() -> new RuntimeException("Unidad no encontrada con ID: "+id_unidad));
        }
    }

    public ConductorEntity mapIdConductorToConductor(Long id_conductor){
        if (id_conductor == null){
            return null;
        }else{
            return conductorRepository.findById(id_conductor).orElseThrow(() -> new RuntimeException("Conductor no encontrado"));
        }
    }
}
