package org.lucky_devs.Transport_Management.repository;

import org.lucky_devs.Transport_Management.persistence.entity.ConductorEntity;
import org.lucky_devs.Transport_Management.dominio.enums.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConductorRepository extends JpaRepository<ConductorEntity, Long> {

    ConductorEntity findByLicencia(String licencia);
    boolean existsByLicencia(String licencia);
    void deleteByLicencia(String licencia);
    List<ConductorEntity> findByEstado(Estado estado);
}
