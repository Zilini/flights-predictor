package com.flightspredictor.flights.domain.airlines.dto;

import com.flightspredictor.flights.domain.airlines.domain.AirlineResponse;

public record AirlineData(
        String opUniqueCarrier,
        String airlineName
        
) {
    public AirlineData(AirlineResponse airline, String opUniqueCarrier) {
        this(
                opUniqueCarrier,
                (airline != null && airline.getAirlineName() != null)
                        ? airline.getAirlineName() : "Aerolínea no encontrada"
        );
    }
}
