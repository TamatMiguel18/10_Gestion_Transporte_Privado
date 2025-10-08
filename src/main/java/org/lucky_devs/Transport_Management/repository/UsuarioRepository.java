package org.lucky_devs.Transport_Management.repository;

import org.lucky_devs.Transport_Management.persistence.entity.UsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuariosEntity, Long> {

    UsuariosEntity findByCorreo(String correo);

    boolean existsByCorreo(String correo);

    @Modifying
    @Query("DELETE FROM UsuariosEntity u WHERE u.correo = :correo")
    void deleteByCorreo(@Param("correo") String correo);
}
