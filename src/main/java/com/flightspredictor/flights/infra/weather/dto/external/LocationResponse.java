package com.flightspredictor.flights.infra.weather.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 * DTO que representa la respuesta de la API de geo codificación de Open-Meteo
 * Mapea la estructura JSON que devuelve la API al buscar coordenadas de una ciudad
 */
@JsonIgnoreProperties(ignoreUnknown = true) // Ignora campos JSON que no están mapeados
public record LocationResponse(
        List<LocationData> results // Array de resultados de ubicaciones encontradas
) {
    
    /**
     * Representa cada ubicación individual dentro del array "results"
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record LocationData(
            String name,        // Nombre de la ciudad
            Double latitude,    // Latitud de la ubicación
            Double longitude,   // Longitud de la ubicación
            String country,     // País donde se encuentra
            String timezone     // Zona horaria
    ) {}
}