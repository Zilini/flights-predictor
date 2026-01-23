package com.flightspredictor.flights.domain.prediction.mapper;

import com.flightspredictor.flights.domain.prediction.ENUM.Prevision;
import com.flightspredictor.flights.domain.prediction.ENUM.Status;
import com.flightspredictor.flights.domain.prediction.dto.ModelPredictionResponse;
import com.flightspredictor.flights.domain.prediction.dto.PredictionResponse;
import org.springframework.stereotype.Component;

@Component
public class PredictionMapper {

    public ModelPredictionResponse mapToModelResponse(PredictionResponse response) {

        Prevision prevision = (response.prediction() == 1) ? Prevision.DELAYED : Prevision.ON_TIME;

        Status status = setStatus(response.probability(), response.threshold());

        return new ModelPredictionResponse(
                prevision,
                response.probability(),
                status
        );
    }

    private Status setStatus(Double probability, Double threshold) {
        if (probability < threshold) {
            return Status.SUCCESS; // Riesgo bajo de retraso
        } else if (probability > 0.75) {
            return Status.CRITICAL; // Riesgo muy alto
        } else {
            return Status.WARNING; // Riesgo moderado (por encima del threshold)
        }
    }
}
