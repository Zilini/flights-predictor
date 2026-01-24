package com.flightspredictor.flights.domain.prediction.service;

import com.flightspredictor.flights.domain.airports.entity.Airport;
import com.flightspredictor.flights.domain.airports.repository.AirportRepository;
import com.flightspredictor.flights.domain.airports.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Servicio de consulta para aeropuertos.
 *
 * Define operaciones de solo lectura relacionadas con aeropuertos,
 * sin exponer detalles de persistencia ni implementación.
 *
 * Esta interfaz permite validar la existencia de aeropuertos
 * a partir de su código IATA.
 */

@Service
@RequiredArgsConstructor
public class  AirportLookupService {

    private final AirportRepository airportRepository;
    private final AirportService airportService;

    /**
     * Verifica si existe un aeropuerto registrado con el código IATA indicado.
     *
     * @param iata código IATA del aeropuerto (3 letras, ej. "JFK")
     * @return true si el aeropuerto existe, false en caso contrario
     */
    @Transactional
    public boolean existsAirportIata(String iata){
        return airportRepository.existsByAirportIataIgnoreCase(iata);
    };

    @Transactional
    public void getAirportExist(String origin, String dest) {
        airportRepository.findByAirportIata(origin)
                .orElseGet(() -> airportRepository.save(airportService.getAirport(origin)));

        airportRepository.findByAirportIata(dest)
                .orElseGet(() -> airportRepository.save(airportService.getAirport(dest)));
    }

    public Optional<Airport> getAirport(String iata) {
        return airportRepository.findByAirportIata(iata);
    }
}