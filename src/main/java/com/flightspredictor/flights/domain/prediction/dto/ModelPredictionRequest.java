package com.flightspredictor.flights.domain.prediction.dto;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public record ModelPredictionRequest(

        Integer dayOfMonth,
        Integer dayOfWeek,
        OffsetDateTime flightDateTime,
        String opUniqueCarrier,
        String origin,
        String dest,
        Double distance,
        Integer crsDepTime
) {}
