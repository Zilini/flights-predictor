package com.flightspredictor.flights.domain.prediction.repository;

import com.flightspredictor.flights.domain.prediction.entity.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PredictionRepository extends JpaRepository<Prediction, Long> {
}
