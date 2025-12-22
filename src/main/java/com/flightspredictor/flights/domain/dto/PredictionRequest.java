package com.flightspredictor.flights.domain.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

public record PredictionRequest(
        @NotBlank(message = "La aerolínea es obligatoria")
        String aerolinea,

        @NotBlank(message = "El aeropuerto de origen es obligatorio")
        String origen,

        @NotBlank(message = "El aeropuerto de destino es obligatorio")
        String destino,

        @NotNull(message = "La fecha de partida es obligatoria")
        @FutureOrPresent(message = "La fecha de partida no puede ser anterior a la fecha actual")
        LocalDateTime fecha_partida,

        @NotNull(message = "La distancia es obligatoria")
        @Positive(message = "La distancia debe ser un valor positivo")
        @DecimalMin(value = "50.0", message = "La distancia mínima permitida es de 50 km")
        @DecimalMax(value = "20000.0", message = "La distancia máxima permitida es de 20.000 km")
        Double distancia_km
) {}
