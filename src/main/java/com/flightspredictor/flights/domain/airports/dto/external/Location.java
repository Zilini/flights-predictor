package com.flightspredictor.flights.domain.airports.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Location(
<<<<<<< HEAD
        Double lat,
        Double lon
=======
        @JsonProperty("lat") Double lat,
        @JsonProperty("lon") Double lon
>>>>>>> efcab86d77a3e7aff7effaef866f586486944dd2
) {
}
