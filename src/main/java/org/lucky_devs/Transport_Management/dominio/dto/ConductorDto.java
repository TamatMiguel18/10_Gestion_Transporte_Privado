package org.lucky_devs.Transport_Management.dominio.dto;
import org.lucky_devs.Transport_Management.dominio.enums.Estado;
import java.time.LocalDate;

public record ConductorDto(
        Long id_Conductor,
        String licencia,
        LocalDate fechaNacimiento,
        Estado estado,
        Long antiguedad
) {
}
