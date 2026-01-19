package com.flightspredictor.flights.domain.prediction.client;

import com.flightspredictor.flights.domain.prediction.dto.ModelPredictionRequest;
import com.flightspredictor.flights.domain.prediction.dto.ModelPredictionResponse;
import com.flightspredictor.flights.domain.prediction.dto.PredictionRequest;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class PredictionApiClient {

    private final WebClient webClient;

    public ModelPredictionResponse predict(ModelPredictionRequest request){
        return webClient
                .post()
                .uri("/predict")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ModelPredictionResponse.class)
                .block();
    }

}
