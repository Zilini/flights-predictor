package com.flightspredictor.flights.domain.service;

import com.flightspredictor.flights.domain.dto.ModelPredictionRequest;
import com.flightspredictor.flights.domain.dto.PredictionRequest;
import com.flightspredictor.flights.domain.dto.ModelPredictionResponse;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import org.springframework.stereotype.Service;


@Service
public class PredictionService {
    /**
     * private final WebClient webClient;
     * private final PredictionRequestValidator requestValidator;
     * private final ModelPredictionResponse responseValidator;
     * 
     * public PredictionService(WebClient.Builder webClientBuilder,
     *                          PredictionRequestValidator requestValidator,
     *                          @Value("${model.api.base-url}") String modelBaseUrl) {
     *      this.webClient = webClientBuilder.baseUrl(modelBaseUrl).build();
     *      this.requestValidator = requestValidator;
     * }
     */

    public ModelPredictionResponse predecirVuelo(PredictionRequest dto){
        if(dto == null){
            return null;
        }
        /**
         * LLamada a la api del modelo y retorno de la respuesta al controller. 
         * 
         * 1ro se llama a la parte de validacion del dto con los datos que envio el usuario
         * requestValidator.validate(dto);
         * 
         * 2do se mapea el dto que manda el usuario al dto que recibo el modelo
         * ModelPredictionRequest modelRequest = mapToModelRequest(dto);
         * 
         * 3ro se utiliza web client para comunicarse con el modelo
         * ModelPredictionResponse modelResponse = webClient.post()
         *      .uri("/end-point-model")
         *      .bodyValue(modelRequest)
         *      .retrieve()
         *      .bodyToMono(ModelPredictionResponse.class)
         *      .block();
         * 
         * 4to se verifica que la respuesta no sea null
         * if (modelResponse == null) {
         *   return null;
         * }
         * 
         * 5to se valida los datos obtenidos por el modelo, se mapea la respuesta al dto de salida y se lo retorna al controller
         * responseValidator.validate(modelResponse);
         * return modelResponse;
         */
        
        return new ModelPredictionResponse("Retrasado", 0.7);
    }

    /**
     * Metodo para mapear los datos que ingresa el usuario hacia el dto que recibe el modelo
     */
    private ModelPredictionRequest mapToModelRequest(PredictionRequest dto) {
        OffsetDateTime flightDateTime = dto.flight_datetime();
        int crsDepTime = (flightDateTime.getHour() * 100) + flightDateTime.getMinute();
        return new ModelPredictionRequest(
                flightDateTime.toLocalDate().getDayOfMonth(),
                flightDateTime.getDayOfWeek().getValue(),
                flightDateTime.toLocalDate(),
                dto.op_unique_carrier(),
                dto.origin(),
                dto.dest(),
                dto.distance(),
                crsDepTime
        );
    }
}
