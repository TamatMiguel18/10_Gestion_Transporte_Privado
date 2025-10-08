package org.lucky_devs.Transport_Management.dominio.dto;

import jakarta.validation.constraints.*;
import org.lucky_devs.Transport_Management.dominio.enums.Tipo_Unidad;

public record UnidadRequestDto(
        @Size(min = 7, max = 7, message = "La placa tiene que tener 7 caracteres.")
        @NotBlank(message = "La placa no puede estar vacia.")
        String placa,
        @NotNull(message = "El modelo no puede estar vacio.")
        String modelo,
        @Min(value = 15, message = "El valor minimo para la capacidad es 15")
        @Max(value = 75, message = "El valor maximo para la capacidad es 75")
        @Positive(message = "El valor de la capacidad no puede ser negativo")
        Long capacidad,
        Tipo_Unidad tipo_unidad
) {
}
