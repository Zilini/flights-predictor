package com.flightspredictor.flights.domain.dto;

public record ModelPredictionResponse(
        String prevision,
        Double probabilidad
) {}
