package com.flightspredictor.flights.domain.prediction.mapper;

import com.flightspredictor.flights.domain.prediction.dto.ModelPredictionRequest;
import com.flightspredictor.flights.domain.prediction.dto.PredictionRequest;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class RequestMapper {
    /**
     * Metodo para mapear los datos que ingresa el usuario hacia el dto que recibe el modelo
     */
    public ModelPredictionRequest mapToModelRequest(PredictionRequest dto, double distance) {
        OffsetDateTime flightDateTime = dto.flightDateTime();

        int crsDepTime = (flightDateTime.getHour() * 100) + flightDateTime.getMinute();

        return new ModelPredictionRequest(
                flightDateTime.toLocalDate().toString(),
                dto.opUniqueCarrier(),
                dto.origin(),
                dto.dest(),
                distance,
                crsDepTime,
                0,
                0.0,
                0.0,
                0.0,
                0.0,
                0.0,
                0.0,
                0.0,
                0.0
        );
    }
}
