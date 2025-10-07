package org.lucky_devs.Transport_Management.web.exception;

import org.lucky_devs.Transport_Management.dominio.exception.*;
import org.lucky_devs.Transport_Management.dominio.exception.Error;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    //Exception para el manejo de placas que ya estan en uso
    @ExceptionHandler(PlacaYaEnUso.class)
    public ResponseEntity<Error> handlerException(PlacaYaEnUso ex){
        Error error = new Error("Placa ya en uso", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    //Exception para el manejo de placas que no existen
    @ExceptionHandler(PlacaNotFound.class)
    public ResponseEntity<Error> handlerException(PlacaNotFound ex){
        Error error = new Error("Placa no existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}
