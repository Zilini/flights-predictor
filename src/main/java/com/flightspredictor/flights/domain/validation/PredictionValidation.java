package com.flightspredictor.flights.domain.validation;

import com.flightspredictor.flights.domain.dto.PredictionRequest;
import com.flightspredictor.flights.domain.error.BusinessErrorCodes;
import com.flightspredictor.flights.domain.error.BusinessException;
import org.springframework.stereotype.Component;

@Component
public class PredictionValidation {
    public void validarReglasDeNegocio(PredictionRequest dto){

        //Crea la validacion (Si origen es igual a destino, retorna el mensaje "El lugar de origen y el destino no pueden ser iguales")
        if (dto.getOrigin()!= null && dto.getDest()!= null && dto.getOrigin().equalsIgnoreCase(dto.getDest())){

                throw new BusinessException(
                        BusinessErrorCodes.INVALIDE_ROUTE,
                        "El lugar de origen y el destino no pueden ser iguales"
                );
        }
    }
}
