package com.flightspredictor.flights.domain.airports.service;

import com.flightspredictor.flights.domain.airports.client.AirportApiClient;
import com.flightspredictor.flights.domain.airports.dto.AirportData;
import com.flightspredictor.flights.domain.airports.entity.Airport;
import com.flightspredictor.flights.domain.airports.repository.AirportRepository;
import com.flightspredictor.flights.domain.airports.validations.IAirportsValidations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportApiClient apiClient;
    private final AirportRepository repository;
    private final List<IAirportsValidations> validations;

      /**
     * Verifica si existe un aeropuerto registrado con el código IATA.
     *
     * La búsqueda se realiza ignorando mayúsculas y minúsculas.
     *
     * @param iata código IATA del aeropuerto
     * @return true si existe, false en caso contrario
     */
    public boolean existsAirportIata(String iata) {
        return repository.existsByAirportIataIgnoreCase(iata);
    }


    public Airport getAirport (String iata) {

        // Busca primero el aeropuerto en la base de datos si ya existe
        return repository.findByAirportIata(iata.toUpperCase())
                .orElseGet(() -> {

                    // Si no existe, trae los datos de la API
                    var apiResponse = apiClient.airportResponse(iata);

                    var data = new AirportData(apiResponse);

                    // Se aplican las validaciones para los campos necesarios para el modelo
                    validations.forEach(v -> v.validate(data));

                    // Guarda en la base de datos y devuelve la información.
                    var airport = new Airport(data);
                    return repository.save(airport);

                });
    }

}
