package org.lucky_devs.Transport_Management.dominio.exception;

public class RutaNotFound extends RuntimeException {
    public RutaNotFound(Long id_ruta) {
        super("No hay ninguna ruta con ese id");
    }
}
