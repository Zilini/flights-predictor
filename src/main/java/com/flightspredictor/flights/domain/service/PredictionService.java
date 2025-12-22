package com.flightspredictor.flights.domain.service;

import com.flightspredictor.flights.domain.dto.PredictionRequest;
import com.flightspredictor.flights.domain.dto.PredictionResponse;
import org.springframework.stereotype.Service;


@Service
public class PredictionService {
    public PredictionResponse predecirVuelo(PredictionRequest dto){
        if(dto != null){
            return new PredictionResponse("Retrasado", 0.7);
        }
        return null;
    }
}
