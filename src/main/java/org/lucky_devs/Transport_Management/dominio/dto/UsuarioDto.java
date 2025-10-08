package org.lucky_devs.Transport_Management.dominio.dto;

public record UsuarioDto(
        Long id_usuario,
        Long id_conductor,
        String correo,
        String contrasena
) {
}
