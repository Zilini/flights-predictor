package com.flightspredictor.flights.domain.airline.service;

import com.flightspredictor.flights.domain.airline.client.AirlineApiClient;
import com.flightspredictor.flights.domain.airline.dto.AirlineData;
import com.flightspredictor.flights.domain.airline.entity.Airline;
import com.flightspredictor.flights.domain.airline.repository.AirlineRepository;
import com.flightspredictor.flights.domain.airline.validations.IAirlineValidations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirlineService {

    private final AirlineApiClient apiClient;
    private final AirlineRepository repository;
    private final List<IAirlineValidations> validations;

    public Airline getAirline(String opUniqueCarrier) {

        // Busca la aerolínea en la base de datos si ya existe
        return repository.findByOpUniqueCarrier(opUniqueCarrier.toUpperCase())
                .orElseGet(() -> {

                    // Si no hay aerolíneas en la base de datos, consulta a la api
                    var apiResponse = apiClient.airlineResponse(opUniqueCarrier);

                    var data = new AirlineData(apiResponse,opUniqueCarrier);

                    // Aplica validaciones para loc campos necesarios
                    validations.forEach(v -> v.validate(data));

                    // Crea la nueva entidad con los datos obtenidos.
                    var airline = new Airline(data);

                    // Guarda en la base de datos para futuras consultas y devuelve la información
                    return repository.save(airline);
                });

    }
}
