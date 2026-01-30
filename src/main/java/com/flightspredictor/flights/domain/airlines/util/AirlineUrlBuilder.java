package com.flightspredictor.flights.domain.airlines.util;

public class AirlineUrlBuilder {

    private static String codeAirline;
    // URL base para el consumo de la API de aerodatabox para aerolíneas
    private static final String BASE_URL =
            "https://prod.api.market/api/v1/aedbx/aerodatabox/airlines/";

    private final static String AIRCRAFT = "/aircrafts";

    private final static Integer PAGE_SIZE_DEFAUL = 1;

    public static String buildAirlineUrl(String opUniqueCarrier) {
        return String.format(
                "%s%s%s?pageSize=%d&pageOffSet=0&withRegistrations=false",
                BASE_URL,
                opUniqueCarrier.toUpperCase(),
                AIRCRAFT,
                PAGE_SIZE_DEFAUL
        );
    }
}
