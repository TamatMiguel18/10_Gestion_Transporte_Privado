package org.lucky_devs.Transport_Management.dominio.exception;

public class RutaYaExiste extends RuntimeException {
    public RutaYaExiste(String direccion_fin) {
        super("Ya hay una ruta con esa direccion de fin");
    }
}
