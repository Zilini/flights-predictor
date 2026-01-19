package com.flightspredictor.flights.domain.prediction.dto;

import com.flightspredictor.flights.domain.prediction.ENUM.Prevision;
import com.flightspredictor.flights.domain.prediction.ENUM.Status;
import com.flightspredictor.flights.domain.prediction.entity.Prediction;
import com.flightspredictor.flights.domain.prediction.entity.Request;

public record ModelPredictionResponse(
        Prevision prevision,
        Double probability,
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
