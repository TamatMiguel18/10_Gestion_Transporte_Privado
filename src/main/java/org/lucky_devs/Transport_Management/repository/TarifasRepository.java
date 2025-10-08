package org.lucky_devs.Transport_Management.repository;

import org.lucky_devs.Transport_Management.persistence.entity.TarifasEntity;
import org.lucky_devs.Transport_Management.dominio.enums.Tipo_Unidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifasRepository extends JpaRepository<TarifasEntity, Long> {
    TarifasEntity findByTipoUnidad(Tipo_Unidad tipoUnidad);
    boolean existsByTipoUnidad(Tipo_Unidad tipoUnidad);
    void deleteByTipoUnidad(Tipo_Unidad tipoUnidad);
}