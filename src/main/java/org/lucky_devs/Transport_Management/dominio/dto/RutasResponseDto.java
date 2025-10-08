package org.lucky_devs.Transport_Management.dominio.dto;

public record RutasResponseDto(
        Long id_ruta,
        Long id_conductor,
        Long id_unidad,
        String direccion_destino
) {
}
