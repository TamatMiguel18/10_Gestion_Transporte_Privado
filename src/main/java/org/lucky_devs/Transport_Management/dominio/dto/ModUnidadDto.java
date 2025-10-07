package org.lucky_devs.Transport_Management.dominio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.lucky_devs.Transport_Management.dominio.enums.Estado_Operativo;
import org.lucky_devs.Transport_Management.dominio.enums.Tipo_Unidad;

public record ModUnidadDto(
        @Size(min = 7, max = 7, message = "La nueva placa tiene que tener 7 caracteres.")
        @NotBlank(message = "La nueva placa no puede estar vacia.")
        String placa,
        @NotNull(message = "El nuevo modelo no puede estar vacio.")
        String modelo,
        Tipo_Unidad tipo_unidad,
        Estado_Operativo estado_operativo
) {
}
