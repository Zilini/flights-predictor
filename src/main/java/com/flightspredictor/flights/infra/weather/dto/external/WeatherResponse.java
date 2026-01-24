package com.flightspredictor.flights.infra.weather.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO que representa la respuesta de la API meteorológica de WeatherAPI
 * Mapea la estructura JSON que devuelve la API con los datos del clima actual
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherResponse(
        CurrentWeather current
) {
    
    /**
     * Representa los datos meteorológicos actuales dentro del objeto "current"
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record CurrentWeather(
            @JsonProperty("temp_c")
            Double temperature,
            
            @JsonProperty("wind_kph")
            Double windSpeed,
            
            @JsonProperty("humidity")
            Integer humidity,
            
            @JsonProperty("pressure_mb")
            Double pressure,

            @JsonProperty("condition")
            Condition condition,

            @JsonProperty("last_updated")
            String lastUpdated
    ) {

        /**
         * Representa las condiciones del clima actual
         */
        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Condition(
                @JsonProperty("text")
                String text,

                @JsonProperty("icon")
                String icon
        ) {}
    }
}