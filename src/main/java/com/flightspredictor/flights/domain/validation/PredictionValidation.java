package com.flightspredictor.flights.domain.validation;

import org.springframework.stereotype.Component;

import com.flightspredictor.flights.domain.requests.dto.PredictionRequest;
import com.flightspredictor.flights.domain.airports.service.AirportService;
import com.flightspredictor.flights.domain.error.BusinessErrorCodes;
import com.flightspredictor.flights.domain.error.BusinessException;
import com.flightspredictor.flights.domain.service.AirportLookupService;

@Component
public class PredictionValidation {

    private final AirportService airportService; // Crear la implementación en service para conectar con la validación

    public void validarReglasDeNegocio(PredictionRequest dto){

        //Crea la validacion (Si origen es igual a destino, retorna el mensaje "El lugar de origen y el destino no pueden ser iguales")
        if (dto.getOrigin()!= null && dto.getDest()!= null && dto.getOrigin().equalsIgnoreCase(dto.getDest())){

                throw new BusinessException(
                        BusinessErrorCodes.INVALID_ROUTE,
                        "El lugar de origen y el destino no pueden ser iguales"
                );
        }
    }

   public PredictionValidation(AirportService airportService) {
        this.airportService = airportService;
    }

    public void ValidarIata(String iataCode){
        //Crea la validación (Si el código IATA no se encuentra dentro de la base de datos, retorna el mensaje "El código IATA: 'xxx' no existe")
        if(!airportService.existsAirportIata(iataCode)){
            throw new BusinessException(
                BusinessErrorCodes.INVALID_IATA,
                "El código IATA" + iataCode + "no existe"
            );
        }
    }
}
