package com.flightspredictor.flights.domain.service;

import com.flightspredictor.flights.domain.airports.service.AirportService;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio de consulta para aeropuertos.
 *
 * Esta clase implementa la interfaz AirportLookupService y delega
 * las operaciones de consulta a AirportService.
 */
@Service
public class AirportLookupServiceImpl implements AirportLookupService {

    private final AirportService airportService;

    public AirportLookupServiceImpl(AirportService airportService) {
        this.airportService = airportService;
    }

    @Override
    public boolean existsAirportIata(String iata) {
        return airportService.existsAirportIata(iata);
    }
}
