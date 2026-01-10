package com.flightspredictor.flights.infra.airports.validations;

import com.flightspredictor.flights.infra.airports.dto.AirportData;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

/**
 * Validador central para datos de aeropuertos.
 * Agrupa todas las validaciones necesarias antes de procesar
 * o persistir un aeropuerto.
 */

@Component
public class AirportValidation {
    /**
     * Punto de entrada único para validar AirportData.
     */
    public void validate(AirportData data){
        validateIata(data);
        validateIataLength(data);
        validateCoordinates(data);
        validateTimezone(data);
    }

    private void validateIata(AirportData data){
        if (data.airportIata() == null || data.airportIata().isBlank()){

            throw new ValidationException("Codigo IATA requerido");

        }
    }

    private void validateIataLength(AirportData data){
        if (data.airportIata().length() != 3){

            throw new ValidationException(
                    "Codigo IATA invalido, el codigo debe de tener 3 letras"
            );

        }
    }

    private void validateCoordinates(AirportData data){
        if (data.longitude() == null || data.latitude() == null){

            throw new ValidationException(
                    "Longitud y Latitud son campos obligatorios"
            );

        }
    }

    private void validateTimezone(AirportData data){
        if (data.timeZone() == null || data.timeZone().isBlank()){

            throw new ValidationException("Zona horaria requerida. Ej: America/Lima");

        }
    }
}
