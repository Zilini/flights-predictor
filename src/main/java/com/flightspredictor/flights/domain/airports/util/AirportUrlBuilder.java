package com.flightspredictor.flights.domain.airports.util;

public class AirportUrlBuilder {

    //URL base para consumo de api de aerodatabox para aeropuertos
    private static final String BASE_URL =
            "https://prod.api.market/api/v1/aedbx/aerodatabox/airports/";

    // Tipo de código para buscar aeropuertos por IATA o ICAO
    // (Default = ICAO)
    private static final String CODE_TYPE_DEFAULT = "Iata";

    private AirportUrlBuilder(){
    }

    // Construye la URL completa para la API que busca los aeropuertos.
    public static String buildAirportUrl(String airportIata) {

        return String.format(
                "%s/%s/%s?withRunways=false&withTime=false",
                BASE_URL,
                CODE_TYPE_DEFAULT,
                airportIata.toUpperCase()
        );
    }
}
