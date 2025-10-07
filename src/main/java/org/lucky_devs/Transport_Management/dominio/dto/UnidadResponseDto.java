package org.lucky_devs.Transport_Management.dominio.dto;

import org.lucky_devs.Transport_Management.dominio.enums.Estado_Operativo;
import org.lucky_devs.Transport_Management.dominio.enums.Tipo_Unidad;

public record UnidadResponseDto(
        Long id_unidad,
        String placa,
        String modelo,
        Long capacidad,
        Tipo_Unidad tipo_unidad,
        Estado_Operativo estado_operativo
) {
}
