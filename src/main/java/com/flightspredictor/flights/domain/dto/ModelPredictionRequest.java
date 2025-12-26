package com.flightspredictor.flights.domain.dto;

import java.time.LocalDate;

public record ModelPredictionRequest(
        Integer day_of_month,
        Integer day_of_week,
        LocalDate fl_date,
        String op_unique_carrier,
        String origin,
        String dest,
        Double distance,
        Integer crs_dep_time
) {}
