package org.lucky_devs.Transport_Management.repository;

import org.lucky_devs.Transport_Management.dominio.dto.ConductorDto;
import org.lucky_devs.Transport_Management.dominio.dto.ModConductor;
import org.lucky_devs.Transport_Management.persistence.entity.ConductorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConductorRepository extends CrudRepository<ConductorEntity, Long>{

}
