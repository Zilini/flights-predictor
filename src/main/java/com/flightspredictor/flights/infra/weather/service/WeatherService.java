package com.flightspredictor.flights.infra.weather.service;

import com.flightspredictor.flights.domain.airports.entity.Airport;
import com.flightspredictor.flights.domain.airports.service.AirportService;
import com.flightspredictor.flights.infra.weather.client.WeatherApiClient;
import com.flightspredictor.flights.infra.weather.dto.WeatherData;
import com.flightspredictor.flights.infra.weather.dto.external.WeatherResponse;
import com.flightspredictor.flights.infra.weather.exception.AirportNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;

import java.util.Locale;

@Service
public class WeatherService {
    
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);
    private final WeatherApiClient weatherApiClient;
    private final AirportService airportService;
    
    public WeatherService(WeatherApiClient weatherApiClient, AirportService airportService) {
        this.weatherApiClient = weatherApiClient;
        this.airportService = airportService;
    }
    
    public WeatherData getWeatherByIata(String iataCode) {
        if (iataCode == null || iataCode.trim().isEmpty()) {
            throw new IllegalArgumentException("El código IATA no puede estar vacío");
        }
        
        String searchCode = iataCode.trim().toUpperCase(Locale.ROOT);
        logger.debug("Buscando aeropuerto con código IATA");
        
        // Buscar aeropuerto por IATA en la base de datos (o cargar desde API si no existe)
        Airport airport = airportService.getAirport(searchCode);
        
        logger.debug("Aeropuerto encontrado: {}", airport != null ? "Sí" : "No");
        
        if (airport == null) {
            throw new AirportNotFoundException("Aeropuerto no encontrado con código IATA: " + iataCode);
        }
        
        // Obtener datos meteorológicos usando las coordenadas del aeropuerto
        WeatherResponse weatherResponse = weatherApiClient.getWeatherData(
                airport.getLatitude(), airport.getLongitude());
        
        if (weatherResponse == null || weatherResponse.current() == null) {
            throw new RuntimeException("No se pudieron obtener datos meteorológicos para: " + iataCode);
        }
        
        return mapToWeatherData(airport, weatherResponse.current());
    }
    
    private WeatherData mapToWeatherData(Airport airport, WeatherResponse.CurrentWeather currentWeather) {
        ZonedDateTime localtime = ZonedDateTime.now();

        WeatherResponse.CurrentWeather.Condition condition = currentWeather.condition();

        return new WeatherData(
                airport.getAirportIata(),
                airport.getAirportName(),
                airport.getCityName(),
                airport.getCountryName(),
                airport.getLatitude(),
                airport.getLongitude(),
                currentWeather.lastUpdated(),
                currentWeather.temperature(),
                currentWeather.humidity().longValue(),
                currentWeather.windSpeed(),
                currentWeather.pressure(),
                condition
        );
    }
}