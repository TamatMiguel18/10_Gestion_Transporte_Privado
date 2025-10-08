package org.lucky_devs.Transport_Management.dominio.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.lucky_devs.Transport_Management.dominio.enums.EstadoReserva;

import java.time.LocalDateTime;

public record ReservaRequestDto(
        @NotNull(message = "El ID de la tarifa es obligatorio.")
        Long idTarifa,
        @NotNull(message = "El ID del cliente es obligatorio.")
        Long idCliente,
        @NotNull(message = "El ID de la ruta es obligatorio.")
        Long idRuta,
        @NotNull(message = "La fecha y hora de inicio son obligatorias.")
        LocalDateTime fechaHoraInicio,
        @NotNull(message = "La fecha y hora de fin son obligatorias.")
        LocalDateTime fechaHoraFin,
        @NotNull(message = "La dirección de origen es obligatoria.")
        @Size(max = 255, message = "La dirección de origen no debe superar los 255 caracteres.")
        String direccionOrigen,
        @NotNull(message = "El estado del viaje es obligatorio.")
        EstadoReserva estadoViaje
) {
}
