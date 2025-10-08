package org.lucky_devs.Transport_Management.dominio.dto;

import org.lucky_devs.Transport_Management.dominio.enums.Tipo_Unidad;

import java.time.LocalDateTime;


public record TarifasDto(
        Long id_tarifa,
        Tipo_Unidad tipoUnidad,
        Double tarifa_base,
        Double recargo,
        LocalDateTime hora_inicio_pico
) {
}