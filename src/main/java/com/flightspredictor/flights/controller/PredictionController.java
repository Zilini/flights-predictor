package com.flightspredictor.flights.controller;

import com.flightspredictor.flights.domain.prediction.dto.PredictionRequest;
import com.flightspredictor.flights.domain.prediction.dto.ModelPredictionResponse;
import com.flightspredictor.flights.domain.prediction.service.PredictionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/predict")
@RequiredArgsConstructor
public class PredictionController {

    private final PredictionService predictionService;

    @PostMapping
    public ResponseEntity<ModelPredictionResponse> predict(@RequestBody @Valid PredictionRequest request) {

        return ResponseEntity.ok(predictionService.predict(request));
    }

    /*@GetMapping("/stats")
    public ResponseEntity<String> getStats() {
        return ResponseEntity.ok(predictionService.obtenerEstadisticas());
    }*/
}
