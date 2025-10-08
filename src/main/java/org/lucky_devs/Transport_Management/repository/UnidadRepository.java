package org.lucky_devs.Transport_Management.repository;

import org.lucky_devs.Transport_Management.persistence.entity.UnidadEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnidadRepository extends CrudRepository<UnidadEntity, Long> {
    UnidadEntity findByPlaca(String placa);
    List<UnidadEntity> findByCapacidad(Long capacidad);
    void deleteByPlaca(String placa);
}
