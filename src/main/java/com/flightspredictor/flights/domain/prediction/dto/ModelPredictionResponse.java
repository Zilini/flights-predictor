package com.flightspredictor.flights.domain.prediction.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.flightspredictor.flights.domain.prediction.ENUM.Prevision;
import com.flightspredictor.flights.domain.prediction.ENUM.Status;
import com.flightspredictor.flights.domain.prediction.entity.Prediction;
import com.flightspredictor.flights.domain.prediction.entity.Request;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ModelPredictionResponse(

        @JsonProperty("prediction")
        Prevision prevision,

        @JsonProperty("probability")
        Double probability,

        @JsonProperty("threshold")
        Status status
) {
    public ModelPredictionResponse(Prediction response) {
        this (
                response.getPrevision(),
                response.getProbability(),
                response.getStatus()
        );
    }
}
