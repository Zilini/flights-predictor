package com.flightspredictor.flights.infra.weather.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import io.netty.channel.ChannelOption;
import java.time.Duration;
import java.util.Locale;

@Configuration
public class WeatherConfig {
    @Value("${api.market.key:}")

    public static final String WEATHER_BASE_URL = "http://api.weatherapi.com/v1/current.json";
    public static final String KEY_API_WEATHER = "82d0e54117ef4fe6822213050261201";
    
    public static final int READ_TIMEOUT_SECONDS = 10;
    public static final int CONNECTION_TIMEOUT_SECONDS = 5;
    
    public static String buildWeatherUrl(double latitude, double longitude) {
        return String.format(Locale.US, "%s?key=%s&q=%.6f,%.6f&aqi=yes",
                WEATHER_BASE_URL, KEY_API_WEATHER, latitude, longitude);
    }
    
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(
                    HttpClient.create()
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECTION_TIMEOUT_SECONDS * 1000)
                        .responseTimeout(Duration.ofSeconds(READ_TIMEOUT_SECONDS))
                ))
                .build();
    }
}