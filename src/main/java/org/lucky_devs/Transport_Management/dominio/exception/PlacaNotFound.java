package org.lucky_devs.Transport_Management.dominio.exception;

public class PlacaNotFound extends RuntimeException {
    public PlacaNotFound(String placa) {
        super("No hay ninguna unidad registrada con esta placa: "+placa+" verifique que este ingresando la correcta.");
    }
}
