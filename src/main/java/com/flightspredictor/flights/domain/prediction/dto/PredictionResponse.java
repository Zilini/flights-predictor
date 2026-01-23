package com.flightspredictor.flights.domain.prediction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.security.interfaces.DSAPublicKey;

public record PredictionResponse(
        @JsonProperty("prediction")
        Integer prediction,

        @JsonProperty("probability")
        Double probability,

        @JsonProperty("threshold")
        Double threshold
) {
}
