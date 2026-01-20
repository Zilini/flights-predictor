package com.flightspredictor.flights.domain.airports.dto;

import com.flightspredictor.flights.domain.airports.domain.AirportResponse;

public record AirportData(
        String airportIata,

        String airportName,
        String country,
        String cityName,
        Double latitude,
        Double longitude,
        Double elevation,
        String timeZone,
        String googleMaps
) {

    public AirportData(AirportResponse airport) {
        this(
                airport.getAirportIata(),
                airport.getAirportName(),
                airport.getCountry().name() !=null ? airport.getCountry().name() : null,
                airport.getCityName(),
                airport.getLocation().lat() != null ? airport.getLocation().lat() : null,
                airport.getLocation().lon() != null ? airport.getLocation().lon() : null,
                airport.getElevation().meter() != null ? airport.getElevation().meter() : null,
                airport.getTimezone(),
                airport.getGoogleMaps() != null
                        ? airport.getGoogleMaps().googleMaps()
                        : null
        );
    }
}
