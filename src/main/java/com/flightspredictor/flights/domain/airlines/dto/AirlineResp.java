package com.flightspredictor.flights.domain.airlines.dto;

import com.flightspredictor.flights.domain.airlines.entity.Airline;

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
