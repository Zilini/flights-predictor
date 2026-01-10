package com.flightspredictor.flights.domain.service;

/**
 * Servicio de consulta para aeropuertos.
 *
 * Define operaciones de solo lectura relacionadas con aeropuertos,
 * sin exponer detalles de persistencia ni implementación.
 *
 * Esta interfaz permite validar la existencia de aeropuertos
 * a partir de su código IATA.
 */
public interface  AirportLookupService {
    /**
     * Verifica si existe un aeropuerto registrado con el código IATA indicado.
     *
     * @param iata código IATA del aeropuerto (3 letras, ej. "JFK")
     * @return true si el aeropuerto existe, false en caso contrario
     */
    boolean existsAirportIata(String iata);
}
