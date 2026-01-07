package com.flightspredictor.flights.domain.error;

public class BusinessException extends RuntimeException {

    /**
     * Excepción de negocio.
     *
     * Representa errores derivados de reglas del dominio,
     * no errores técnicos ni de validación.
     *
     * Ejemplo:
     * - Origen y destino iguales
     * -
     */
    private final String code;

    public BusinessException(String code, String message){
        super(message);
        this.code = code;
    }

    public String getCode(){
        return code;
    }

}
