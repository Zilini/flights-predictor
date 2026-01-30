package com.flightspredictor.flights.infra.exception;

public class ExternalApiException extends RuntimeException{

    public ExternalApiException (String message) {
        super(message);
    }
}
