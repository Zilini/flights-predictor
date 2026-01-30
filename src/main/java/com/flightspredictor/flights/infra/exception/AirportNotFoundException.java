package com.flightspredictor.flights.infra.exception;

public class AirportNotFoundException extends RuntimeException {

    public AirportNotFoundException(String iata) {
        super("Aeropuerto no encontrado por el IATA: " + iata);
    }
}
