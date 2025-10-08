package org.lucky_devs.Transport_Management.repository;

import org.lucky_devs.Transport_Management.dominio.dto.RutasRequestDto;
import org.lucky_devs.Transport_Management.dominio.dto.RutasResponseDto;
import org.lucky_devs.Transport_Management.persistence.entity.RutaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RutasRepository extends CrudRepository<RutaEntity, Long> {
    @Query("SELECT r FROM RutaEntity r WHERE r.direccion_destino = :destino")
    RutaEntity findRutaByDestino(@Param("destino") String direccion_destino);
}
