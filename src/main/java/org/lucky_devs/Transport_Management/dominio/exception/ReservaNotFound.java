package org.lucky_devs.Transport_Management.dominio.exception;

public class ReservaNotFound extends RuntimeException {
    public ReservaNotFound(Long idReserva) {
        super("No se encontró ninguna reserva registrada con el ID: " + idReserva);
    }
}
