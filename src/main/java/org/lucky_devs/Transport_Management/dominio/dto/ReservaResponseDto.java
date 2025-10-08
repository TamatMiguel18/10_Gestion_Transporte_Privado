package org.lucky_devs.Transport_Management.dominio.dto;

import org.lucky_devs.Transport_Management.dominio.enums.EstadoReserva;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ReservaResponseDto(
        Long idReserva,
        Long idTarifa,
        Long idCliente,
        Long idRuta,
        LocalDateTime fechaHoraInicio,
        LocalDateTime fechaHoraFin,
        String direccionOrigen,
        EstadoReserva estadoViaje,
        BigDecimal precioFinal
) {
}
