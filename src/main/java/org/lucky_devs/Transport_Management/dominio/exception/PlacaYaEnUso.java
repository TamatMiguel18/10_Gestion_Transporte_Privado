package org.lucky_devs.Transport_Management.dominio.exception;

public class PlacaYaEnUso extends RuntimeException {
    public PlacaYaEnUso(String placa) {
        super("Ya existe una unidad registrada con dicha placa, por favor verifique que este ingresando la correcta.");
    }
}
