package com.flightspredictor.flights.domain.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import java.time.OffsetDateTime;

public record PredictionRequest(
        @NotNull(message = "La fecha y hora de vuelo son obligatorias")
        @FutureOrPresent(message = "La fecha y hora de vuelo deben ser presentes o futuras")
        OffsetDateTime flight_datetime,

        @NotBlank(message = "El código de la aerolínea es obligatorio")
        @Pattern(regexp = "^[A-Z0-9]{2}$", message = "El código de la aerolínea debe tener 2 caracteres IATA")
        String op_unique_carrier,

        @NotBlank(message = "El aeropuerto de origen es obligatorio")
        @Pattern(regexp = "^[A-Z]{3}$", message = "El origen debe ser un código IATA de 3 letras")
        String origin,

        @NotBlank(message = "El aeropuerto de destino es obligatorio")
        @Pattern(regexp = "^[A-Z]{3}$", message = "El destino debe ser un código IATA de 3 letras")
        String dest,

        @NotNull(message = "La distancia es obligatoria")
        @Positive(message = "La distancia debe ser un valor positivo")
        @DecimalMin(value = "50.0", message = "La distancia mínima permitida es de 50 km")
        @DecimalMax(value = "20000.0", message = "La distancia máxima permitida es de 20.000 km")
        Double distance
) {
    public String getOrigin() {
        return origin;
    }

    public String getDest(){
        return dest;
    }
}
