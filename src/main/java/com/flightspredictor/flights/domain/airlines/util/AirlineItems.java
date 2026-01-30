package com.flightspredictor.flights.domain.airlines.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AirlineItems {

    @JsonProperty("airlineName")
    private String airlineName;
}
