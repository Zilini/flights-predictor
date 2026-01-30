package com.flightspredictor.flights.infra.weather.client;

import com.flightspredictor.flights.infra.weather.config.WeatherConfig;
import com.flightspredictor.flights.infra.weather.dto.external.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import java.time.Duration;

@Component
public class WeatherApiClient {
    
    private static final Logger logger = LoggerFactory.getLogger(WeatherApiClient.class);
    private final WebClient webClient;
    
    public WeatherApiClient(WebClient webClient) {
        this.webClient = webClient;
    }
    
    public WeatherResponse getWeatherData(double latitude, double longitude) {
        // Validate coordinates to prevent SSRF
        if (latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Coordenadas inválidas");
        }
        
        try {
            String url = WeatherConfig.buildWeatherUrl(latitude, longitude);
            logger.debug("Calling weather API for coordinates: {}, {}", latitude, longitude);
            
            WeatherResponse response = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(WeatherResponse.class)
                    .timeout(Duration.ofSeconds(WeatherConfig.READ_TIMEOUT_SECONDS))
                    .block();
                    
            logger.debug("Weather API response received successfully");
            return response;
                    
        } catch (WebClientResponseException e) {
            logger.error("Weather API error - Status: {}, Message: {}", e.getStatusCode(), e.getMessage());
            throw new RuntimeException("Error al obtener datos meteorológicos. Código de error: " + e.getStatusCode(), e);
        } catch (Exception e) {
            logger.error("Weather API connection error: {}", e.getMessage());
            throw new RuntimeException("Error de conexión al obtener datos meteorológicos", e);
        }
    }
}