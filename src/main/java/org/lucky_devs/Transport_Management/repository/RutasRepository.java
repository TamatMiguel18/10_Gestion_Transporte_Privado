package org.lucky_devs.Transport_Management.repository;

import org.lucky_devs.Transport_Management.dominio.dto.RutasRequestDto;
import org.lucky_devs.Transport_Management.dominio.dto.RutasResponseDto;
import org.lucky_devs.Transport_Management.persistence.entity.RutaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RutasRepository extends CrudRepository<RutaEntity, Long> {
    RutaEntity findByDireccion_Destino(String direccion_destino);
}
