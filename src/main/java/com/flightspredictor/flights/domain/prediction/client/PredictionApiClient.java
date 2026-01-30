package com.flightspredictor.flights.domain.prediction.client;

import com.flightspredictor.flights.domain.prediction.dto.ModelPredictionRequest;
import com.flightspredictor.flights.domain.prediction.dto.PredictionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class PredictionApiClient {

    private final WebClient webClient;

    public PredictionResponse predict(ModelPredictionRequest request){

        Map<String, Object> body = Map.of("flight", request);

        return webClient
                .post()
                .uri("/predict")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(PredictionResponse.class)
                .block();
    }

}
