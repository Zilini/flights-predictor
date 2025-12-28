package com.flightspredictor.flights.infra.weather.service;

import com.flightspredictor.flights.infra.weather.client.WeatherApiClient;
import com.flightspredictor.flights.infra.weather.dto.WeatherData;
import com.flightspredictor.flights.infra.weather.dto.external.LocationResponse;
import com.flightspredictor.flights.infra.weather.dto.external.WeatherResponse;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Servicio principal para obtener y procesar datos meteorológicos
 * Orquesta las llamadas al cliente HTTP y transforma los datos externos a formato interno
 */
@Service
public class WeatherService {
    
    private final WeatherApiClient weatherApiClient;
    
    /**
     * Constructor que inyecta el cliente HTTP para APIs meteorológicas
     * 
     * @param weatherApiClient cliente configurado para llamadas a Open-Meteo
     */
    public WeatherService(WeatherApiClient weatherApiClient) {
        this.weatherApiClient = weatherApiClient;
    }
    
    /**
     * Método principal que obtiene datos meteorológicos completos para una ciudad
     * Combina geo codificación + datos meteorológicos en una sola operación
     * 
     * @param cityName nombre de la ciudad a consultar
     * @return WeatherData con información meteorológica completa o null si no se encuentra la ciudad
     * @throws RuntimeException si hay errores en las APIs externas
     */
    public WeatherData getWeatherForCity(String cityName) {
        // Validación de entrada
        if (cityName == null || cityName.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la ciudad no puede estar vacío");
        }
        
        try {
            // Paso 1: Obtener coordenadas geográficas de la ciudad
            LocationResponse locationResponse = weatherApiClient.getLocationData(cityName.trim());
            
            // Verificar que se encontraron resultados
            if (locationResponse == null || locationResponse.results() == null || locationResponse.results().isEmpty()) {
                return null; // Ciudad no encontrada
            }
            
            // Tomar el primer resultado (más relevante)
            LocationResponse.LocationData location = locationResponse.results().get(0);
            
            // Paso 2: Obtener datos meteorológicos usando las coordenadas
            WeatherResponse weatherResponse = weatherApiClient.getWeatherData(
                    location.latitude(), 
                    location.longitude()
            );
            
            // Verificar que se obtuvieron datos meteorológicos
            if (weatherResponse == null || weatherResponse.current() == null) {
                throw new RuntimeException("No se pudieron obtener datos meteorológicos para: " + cityName);
            }
            
            // Paso 3: Transformar datos externos a formato interno
            return mapToWeatherData(location, weatherResponse.current());
            
        } catch (Exception e) {
            // Re-lanzar con contexto adicional
            throw new RuntimeException("Error al obtener datos meteorológicos para la ciudad: " + cityName, e);
        }
    }
    
    /**
     * Método de conveniencia para obtener solo datos meteorológicos básicos
     * Útil cuando ya se tienen las coordenadas
     * 
     * @param latitude latitud de la ubicación
     * @param longitude longitud de la ubicación
     * @return WeatherData con información meteorológica básica
     */
    public WeatherData getWeatherForCoordinates(double latitude, double longitude) {
        try {
            // Obtener datos meteorológicos directamente
            WeatherResponse weatherResponse = weatherApiClient.getWeatherData(latitude, longitude);
            
            if (weatherResponse == null || weatherResponse.current() == null) {
                throw new RuntimeException("No se pudieron obtener datos meteorológicos para las coordenadas: " + 
                                         latitude + ", " + longitude);
            }
            
            // Crear LocationData básico para el mapeo
            LocationResponse.LocationData basicLocation = new LocationResponse.LocationData(
                    "Ubicación desconocida", latitude, longitude, "Desconocido", "UTC"
            );
            
            return mapToWeatherData(basicLocation, weatherResponse.current());
            
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener datos meteorológicos para coordenadas: " + 
                                     latitude + ", " + longitude, e);
        }
    }
    
    /**
     * Método privado que transforma los DTOs externos a nuestro DTO interno
     * Centraliza la lógica de mapeo y conversión de datos
     * 
     * @param location datos de ubicación de la API de geo codificación
     * @param currentWeather datos meteorológicos de la API del clima
     * @return WeatherData con datos transformados y listos para usar
     */
    private WeatherData mapToWeatherData(LocationResponse.LocationData location, 
                                       WeatherResponse.CurrentWeather currentWeather) {
        
        // Convertir timestamp string a LocalDateTime
        LocalDateTime measurementTime = parseWeatherTimestamp(currentWeather.time());
        
        // Crear y retornar el DTO interno con todos los datos mapeados
        return new WeatherData(
                location.name(),                        // Nombre de la ciudad
                location.latitude(),                    // Latitud
                location.longitude(),                   // Longitud
                measurementTime,                        // Tiempo de medición convertido
                currentWeather.temperature(),           // Temperatura en Celsius
                currentWeather.relativeHumidity(),      // Humedad relativa
                currentWeather.windSpeed(),             // Velocidad del viento
                location.country()                      // País
        );
    }
    
    /**
     * Método utilitario para convertir el timestamp de la API a LocalDateTime
     * Maneja el formato específico que devuelve Open-Meteo
     * 
     * @param timeString timestamp en formato string de la API
     * @return LocalDateTime parseado o tiempo actual sí hay error
     */
    private LocalDateTime parseWeatherTimestamp(String timeString) {
        try {
            // Open-Meteo devuelve timestamps en formato ISO-8601: "2024-01-15T14:30"
            return LocalDateTime.parse(timeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (Exception e) {
            // Sí hay error en el parsing, usar tiempo actual como fallback
            return LocalDateTime.now();
        }
    }
}