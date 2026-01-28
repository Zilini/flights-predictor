package com.flightspredictor.flights.domain.airline.dto;

import com.flightspredictor.flights.domain.airline.domain.AirlineResponse;

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
