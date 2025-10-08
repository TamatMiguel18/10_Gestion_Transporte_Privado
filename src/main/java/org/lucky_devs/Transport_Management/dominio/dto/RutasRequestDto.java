package org.lucky_devs.Transport_Management.dominio.dto;

import jakarta.validation.constraints.NotNull;

public record RutasRequestDto(
        @NotNull(message = "El id del conductor no puede estar vacio.")
        Long id_conductor,
        @NotNull(message = "El id de la unidad no puede estar vacio.")
        Long id_unidad,
        @NotNull(message = "La direccion de destino no puedo estar vacia.")
        String direccion_destino
) {
}
