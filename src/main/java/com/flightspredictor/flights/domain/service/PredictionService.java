package com.flightspredictor.flights.domain.service;

import com.flightspredictor.flights.domain.prediction.entity.Prediction;
import com.flightspredictor.flights.domain.prediction.repository.PredictionRepository;
import com.flightspredictor.flights.domain.requests.dto.ModelPredictionRequest;
import com.flightspredictor.flights.domain.requests.dto.PredictionRequest;
import com.flightspredictor.flights.domain.requests.dto.ModelPredictionResponse;

import java.time.OffsetDateTime;
import org.springframework.stereotype.Service;

@Service
public class PredictionService {

    private final PredictionRepository predictionRepository;

    // Inyectamos el repositorio en el constructor
    public PredictionService(PredictionRepository predictionRepository) {
        this.predictionRepository = predictionRepository;
    }

    public ModelPredictionResponse predecirVuelo(PredictionRequest dto){
        if(dto == null){
            return null;
        }

        // 1. SIMULACIÓN: Aquí iría la llamada real a la IA de Data Science
        // Por ahora simulamos que la IA responde "Retrasado" con 70% de probabilidad
        ModelPredictionResponse respuestaIA = new ModelPredictionResponse("Retrasado", 0.7);

        // 2. PERSISTENCIA: ¡Aquí es donde cumples tu tarea! Guardamos la predicción en BD.
        Prediction nuevaPrediccion = new Prediction(respuestaIA);
        
        // (Opcional: Podrías guardar también el 'Request' asociado, pero para el MVP esto basta)
        predictionRepository.save(nuevaPrediccion);

        return respuestaIA;
    }

    /**
     * Calcula el porcentaje de éxito del modelo comparando predicciones vs realidad.
     */
    public String obtenerEstadisticas() {
        long total = predictionRepository.countTotalVerificados();
        long aciertos = predictionRepository.countAciertos();

        if (total == 0) {
            return "No hay suficientes datos verificados para calcular estadísticas (0 vuelos procesados).";
        }

        double porcentaje = ((double) aciertos / total) * 100;
        return String.format("Precisión del Modelo: %.2f%% (%d aciertos de %d vuelos)", porcentaje, aciertos, total);
    }
    
    // Método auxiliar (se mantiene por si lo usas luego)
    private ModelPredictionRequest mapToModelRequest(PredictionRequest dto) {
        OffsetDateTime flightDateTime = dto.flight_datetime();
        int crsDepTime = (flightDateTime.getHour() * 100) + flightDateTime.getMinute();
        return new ModelPredictionRequest(
                flightDateTime.toLocalDate().getDayOfMonth(),
                flightDateTime.getDayOfWeek().getValue(),
                flightDateTime.toLocalDate(),
                dto.op_unique_carrier(),
                dto.origin(),
                dto.dest(),
                dto.distance(),
                crsDepTime
        );
    }
}