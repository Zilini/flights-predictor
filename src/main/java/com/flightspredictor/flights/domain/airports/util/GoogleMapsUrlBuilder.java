package com.flightspredictor.flights.domain.airports.util;

public class GoogleMapsUrlBuilder {

    /*URL base para crear ubicación de Google Maps para guardar
    en la base de datos*/
    private static final String URL_BASE_MAPS =
            "https://www.google.com/maps?q=";

    private GoogleMapsUrlBuilder(){
    }

    public static String buildGoogleMapURl(Double latitude, Double longitude) {
        return URL_BASE_MAPS + latitude + "," + longitude;
    }
}
