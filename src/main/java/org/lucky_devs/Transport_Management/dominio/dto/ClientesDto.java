package org.lucky_devs.Transport_Management.dominio.dto;

import org.lucky_devs.Transport_Management.dominio.enums.Genero;

public record ClientesDto(
        Long id_cliente,
        String nombre,
        String correo,
        String telefono,
        Long edad,
        Genero genero
) {
}
