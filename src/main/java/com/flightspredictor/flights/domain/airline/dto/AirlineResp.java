package com.flightspredictor.flights.domain.airline.dto;

import com.flightspredictor.flights.domain.airline.entity.Airline;

public record AirlineResp(
        String opUniqueCarrier,
        String airlineName
) {
    public AirlineResp(Airline airline){
        this(
                airline.getOpUniqueCarrier(),
                airline.getAirlineName()
        );
    }
}
