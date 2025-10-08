package org.lucky_devs.Transport_Management.repository;

import org.lucky_devs.Transport_Management.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRepository extends JpaRepository<ClienteEntity, Long> {
    ClienteEntity findByCorreo(String correo);
    boolean existsByCorreo(String correo);
    void deleteByCorreo(String correo);
}
