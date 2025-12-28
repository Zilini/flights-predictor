package com.flightspredictor.flights.infra.weather.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO que representa la respuesta de la API meteorológica de Open-Meteo
 * Mapea la estructura JSON que devuelve la API con los datos del clima actual
 */
@JsonIgnoreProperties(ignoreUnknown = true) // Ignora campos JSON que no están mapeados
public record WeatherResponse(
        CurrentWeather current // Objeto que contiene los datos meteorológicos actuales
) {
    
    /**
     * Representa los datos meteorológicos actuales dentro del objeto "current"
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record CurrentWeather(
            String time,                                    // Timestamp del momento de la medición
            
            @JsonProperty("temperature_2m")                 // Mapea el campo JSON "temperature_2m"
            Double temperature,                             // Temperatura actual en Celsius
            
            @JsonProperty("relative_humidity_2m")           // Mapea el campo JSON "relative_humidity_2m"
            Long relativeHumidity,                          // Humedad relativa en porcentaje
            
            @JsonProperty("wind_speed_10m")                 // Mapea el campo JSON "wind_speed_10m"
            Double windSpeed                                // Velocidad del viento en km/h
    ) {}
}