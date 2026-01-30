package com.flightspredictor.flights.domain.prediction.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PredictionRequest(

        @NotNull(message = "La fecha y hora de vuelo son obligatorias")
        @FutureOrPresent(message = "La fecha y hora de vuelo deben ser presentes o futuras")
        @JsonProperty("fl_date")
        OffsetDateTime flightDateTime,

        @NotBlank(message = "El código de la aerolínea es obligatorio")
        @Pattern(regexp = "^[A-Z0-9]{2}$", message = "El código de la aerolínea debe tener 2 caracteres IATA")
        @JsonProperty("op_unique_carrier")
        String opUniqueCarrier,

        @NotBlank(message = "El aeropuerto de origen es obligatorio")
        @Pattern(regexp = "^[A-Z]{3}$", message = "El origen debe ser un código IATA de 3 letras")
        String origin,

        @NotBlank(message = "El aeropuerto de destino es obligatorio")
        @Pattern(regexp = "^[A-Z]{3}$", message = "El destino debe ser un código IATA de 3 letras")
        String dest
) {
    public String getOrigin() {
        return origin;
    }

    public String getDest(){
        return dest;
    }
}
