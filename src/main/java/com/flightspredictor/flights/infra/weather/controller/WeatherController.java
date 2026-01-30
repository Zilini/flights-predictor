package com.flightspredictor.flights.infra.weather.controller;

import com.flightspredictor.flights.infra.weather.dto.WeatherData;
import com.flightspredictor.flights.infra.weather.service.WeatherService;
import com.flightspredictor.flights.infra.weather.exception.AirportNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    
    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);
    private final WeatherService weatherService;
    
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
    
    @GetMapping("/{iataCode}")
    public ResponseEntity<WeatherData> getWeatherByIata(@PathVariable String iataCode) {
        try {
            WeatherData weatherData = weatherService.getWeatherByIata(iataCode);
            return ResponseEntity.ok(weatherData);
            
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid IATA code provided: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (AirportNotFoundException e) {
            logger.warn("Airport not found: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            logger.error("Weather service error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}