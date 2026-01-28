package com.flightspredictor.flights.domain.airline.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.flightspredictor.flights.domain.airline.util.AirlineItems;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AirlineResponse {

    @JsonProperty("items")
    private List<AirlineItems> airlineItem;

    public String getAirlineName () {
        if (airlineItem != null && !airlineItem.isEmpty()) {
            return airlineItem.get(0).getAirlineName();
        }
        return null;
    }
}
