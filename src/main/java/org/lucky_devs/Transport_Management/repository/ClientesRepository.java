package org.lucky_devs.Transport_Management.repository;

import org.lucky_devs.Transport_Management.persistence.entity.ClientesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRepository extends JpaRepository<ClientesEntity, Long> {
    ClientesEntity findByCorreo(String correo);
    boolean existsByCorreo(String correo);
    void deleteByCorreo(String correo);
}
