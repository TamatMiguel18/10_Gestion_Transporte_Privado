package org.lucky_devs.Transport_Management.persistence.mapper;

import org.lucky_devs.Transport_Management.dominio.dto.TarifasDto;
import org.lucky_devs.Transport_Management.persistence.entity.TarifasEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarifasMapper {

    @Mapping(source = "id_tarifa", target = "id_tarifa")
    @Mapping(source = "tipoUnidad", target = "tipoUnidad")
    @Mapping(source = "tarifa_base", target = "tarifa_base")
    @Mapping(source = "recargo", target = "recargo")
    @Mapping(source = "hora_inicio_pico", target = "hora_inicio_pico")
    TarifasEntity toEntity(TarifasDto dto);

    @Mapping(source = "id_tarifa", target = "id_tarifa")
    @Mapping(source = "tipoUnidad", target = "tipoUnidad")
    @Mapping(source = "tarifa_base", target = "tarifa_base")
    @Mapping(source = "recargo", target = "recargo")
    @Mapping(source = "hora_inicio_pico", target = "hora_inicio_pico")
    TarifasDto toDto(TarifasEntity entity);

    List<TarifasDto> toDtoList(Iterable<TarifasEntity> entities);

    void updateEntityFromDto(TarifasDto dto, @MappingTarget TarifasEntity entity);
}