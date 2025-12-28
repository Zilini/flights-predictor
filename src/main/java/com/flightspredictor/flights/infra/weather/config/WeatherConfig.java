package com.flightspredictor.flights.infra.weather.config;

import org.springframework.context.annotation.Configuration;

/**
 * Configuración para la integración con la API meteorológica de Open-Meteo
 * Centraliza las URLs, timeouts y parámetros de configuración
 */
@Configuration
public class WeatherConfig {
    
    // URLs base para las APIs de Open-Meteo
    public static final String GEOCODING_BASE_URL = "https://geocoding-api.open-meteo.com/v1";
    public static final String WEATHER_BASE_URL = "https://api.open-meteo.com/v1";
    
    // Parámetros de configuración
    public static final int CONNECTION_TIMEOUT_SECONDS = 10;
    public static final int READ_TIMEOUT_SECONDS = 30;
    public static final String DEFAULT_LANGUAGE = "en";
    public static final int DEFAULT_SEARCH_COUNT = 1;
    
    /**
     * Construye la URL completa para la API de geocodificación
     * 
     * @param cityName nombre de la ciudad a buscar
     * @return URL completa para la consulta de geocodificación
     */
    public static String buildGeocodingUrl(String cityName) {
        // Reemplaza espacios por '+' para la URL
        String formattedCity = cityName.replaceAll(" ", "+");
        
        return String.format("%s/search?name=%s&count=%d&language=%s&format=json",
                GEOCODING_BASE_URL, formattedCity, DEFAULT_SEARCH_COUNT, DEFAULT_LANGUAGE);
    }
    
    /**
     * Construye la URL completa para la API meteorológica
     * 
     * @param latitude latitud de la ubicación
     * @param longitude longitud de la ubicación
     * @return URL completa para la consulta meteorológica
     */
    public static String buildWeatherUrl(double latitude, double longitude) {
        return String.format("%s/forecast?latitude=%f&longitude=%f&current=temperature_2m,relative_humidity_2m,wind_speed_10m",
                WEATHER_BASE_URL, latitude, longitude);
    }
}