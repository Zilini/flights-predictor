package com.flightspredictor.flights.domain.airline.validations;

import com.flightspredictor.flights.domain.airline.dto.AirlineData;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class AirlineValidations {

    public void validate(AirlineData data) {
        validateOpUniqueCarrier(data);
        validateOpUniqueCarrierLength(data);
    }

    private void validateOpUniqueCarrier(AirlineData data) {
        if (data.opUniqueCarrier() == null || data.opUniqueCarrier().isBlank()) {
            throw new ValidationException("Codigo op_unique_carrier requerido.");
        }
    }

    private void validateOpUniqueCarrierLength(AirlineData data) {
        if (data.opUniqueCarrier().length() != 2) {
            throw new ValidationException(
                    "Código op_unique_carrier inválido, el código debe tener 2 letras."
            );
        }
    }
}
