package org.lucky_devs.Transport_Management.dominio.dto;

import org.lucky_devs.Transport_Management.dominio.enums.Estado;

import java.util.Date;

public record ModConductor(
        String licencia,
        String Nombre,
        Date fechaNacimiento,
        Estado estado,
        String dreccion,
        String telefono
) {
}
