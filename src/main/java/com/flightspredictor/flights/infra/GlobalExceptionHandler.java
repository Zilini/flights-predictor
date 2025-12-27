package com.flightspredictor.flights.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlerValidationError(MethodArgumentNotValidException ex){
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errores.put(error.getField(), error.getDefaultMessage())
                );
        Map<String, Object> response = new HashMap<>();
        response.put("Estado", HttpStatus.BAD_REQUEST.value());
        response.put("Error", "Error de Validacion");
        response.put("Detalles", errores);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
