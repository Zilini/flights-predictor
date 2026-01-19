package com.flightspredictor.flights.domain.prediction.repository;

import com.flightspredictor.flights.domain.prediction.entity.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictionRepository extends JpaRepository<Prediction, Long> {

    // 1. Cuenta cuántos vuelos ya tienen un resultado real cargado (para saber el total de muestras)
    @Query("SELECT COUNT(p) FROM Prediction p WHERE p.resultadoReal IS NOT NULL")
    long countTotalVerificados();

    // 2. Cuenta cuántos aciertos hubo (Predicción IA == Resultado Real)
    // Se asume que el String coincide exactamente (ej: "DELAYED" vs "DELAYED")
    @Query("SELECT COUNT(p) FROM Prediction p WHERE p.resultadoReal IS NOT NULL AND p.prevision = p.resultadoReal")
    long countAciertos();
}