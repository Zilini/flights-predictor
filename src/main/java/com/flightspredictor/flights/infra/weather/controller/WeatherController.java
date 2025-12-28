package com.flightspredictor.flights.infra.weather.controller;

import com.flightspredictor.flights.infra.weather.dto.WeatherData;
import com.flightspredictor.flights.infra.weather.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para endpoints relacionados con datos meteorológicos
 * Proporciona acceso HTTP a los servicios de clima para testing y uso externo
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {
    
    private final WeatherService weatherService;
    
    /**
     * Constructor que inyecta el servicio meteorológico
     * 
     * @param weatherService servicio para obtener datos del clima
     */
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
    
    /**
     * Endpoint para obtener datos meteorológicos de una ciudad específica
     * 
     * @param cityName nombre de la ciudad a consultar
     * @return ResponseEntity con WeatherData o 404 si no se encuentra la ciudad
     */
    @GetMapping("/city/{cityName}")
    public ResponseEntity<WeatherData> getWeatherByCity(@PathVariable String cityName) {
        try {
            // Llamar al servicio para obtener datos meteorológicos
            WeatherData weatherData = weatherService.getWeatherForCity(cityName);
            
            // Si no se encuentra la ciudad, retornar 404
            if (weatherData == null) {
                return ResponseEntity.notFound().build();
            }
            
            // Retornar datos meteorológicos encontrados
            return ResponseEntity.ok(weatherData);
            
        } catch (IllegalArgumentException e) {
            // Error de validación de entrada - 400 Bad Request
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            // Error interno del servidor - 500 Internal Server Error
            return ResponseEntity.internalServerError().build();
        }
    }
    
    /**
     * Endpoint para obtener datos meteorológicos usando coordenadas específicas
     * 
     * @param latitude latitud de la ubicación
     * @param longitude longitud de la ubicación
     * @return ResponseEntity con WeatherData
     */
    @GetMapping("/coordinates")
    public ResponseEntity<WeatherData> getWeatherByCoordinates(
            @RequestParam Double latitude, 
            @RequestParam Double longitude) {
        try {
            // Validación básica de coordenadas
            if (latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180) {
                return ResponseEntity.badRequest().build();
            }
            
            // Obtener datos meteorológicos por coordenadas
            WeatherData weatherData = weatherService.getWeatherForCoordinates(latitude, longitude);
            
            return ResponseEntity.ok(weatherData);
            
        } catch (Exception e) {
            // Error interno del servidor
            return ResponseEntity.internalServerError().build();
        }
    }
}