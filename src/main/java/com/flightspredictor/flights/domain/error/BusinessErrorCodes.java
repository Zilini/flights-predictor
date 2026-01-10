package com.flightspredictor.flights.domain.error;

public final class BusinessErrorCodes {

    private BusinessErrorCodes(){
        // Evita la instanciación
    }

    public static final String INVALID_ROUTE = "INVALID_ROUTE";
    public static final String INVALID_IATA = "INVALID_IATA_CODE";
}
