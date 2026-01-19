package com.flightspredictor.flights.domain.prediction.service;

import com.flightspredictor.flights.domain.prediction.client.PredictionApiClient;
import com.flightspredictor.flights.domain.prediction.dto.ModelPredictionRequest;
import com.flightspredictor.flights.domain.prediction.dto.ModelPredictionResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Optional;

import com.flightspredictor.flights.domain.prediction.dto.PredictionRequest;
import com.flightspredictor.flights.domain.prediction.entity.Prediction;
import com.flightspredictor.flights.domain.prediction.entity.Request;
import com.flightspredictor.flights.domain.prediction.repository.PredictionRepository;
import com.flightspredictor.flights.domain.prediction.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PredictionService {

    private final AirportLookupService airportLookupService;
    private final PredictionApiClient predictionClient;
    private final RequestRepository requestRepo;
    private final PredictionRepository predictionRepo;

    public ModelPredictionResponse predict(PredictionRequest request){

        //Busca los aeropueros desde la request para guardarlos en base de datos si no existen
        airportLookupService.getAirportExist(
                request.origin(),
                request.dest()
        );

        //Busca si no existe una request en la base de datos
        Optional<Request> existingRequest =
                requestRepo.
                        findByFlightDateTimeAndOpUniqueCarrierAndOriginAndDestAndDistance(
                                request.flightDateTime(),
                                request.opUniqueCarrier(),
                                request.origin(),
                                request.dest(),
                                request.distance()
            );

        // Si exite, devuelve la predicción asociada a request
        if (existingRequest.isPresent()) {
            Prediction prediction = existingRequest.get().getPrediction();

            return new ModelPredictionResponse(prediction);
        }

        // Mapea la request para entregarla al modelo
        ModelPredictionRequest requestModel = mapToModelRequest(request);

        //Si no exite, hace la llamada al modelo
        ModelPredictionResponse response = predictionClient.predict(requestModel);

        // Construye las entidades para ser almacenadas en la base de datos
        Request requestEntity = new Request(request);
        Prediction predictionEntity = new Prediction(response, requestEntity);

        // Hace la vinculación de la request con la predicción
        requestEntity.setPrediction(predictionEntity);

        // Guarda la request y a la vez la prediction asociada a ella
        requestRepo.save(requestEntity);

        return response;
    }

    /**
     * Metodo para mapear los datos que ingresa el usuario hacia el dto que recibe el modelo
     */
    private ModelPredictionRequest mapToModelRequest(PredictionRequest dto) {
        OffsetDateTime flightDateTime = dto.flightDateTime();

        int crsDepTime = (flightDateTime.getHour() * 100) + flightDateTime.getMinute();

        return new ModelPredictionRequest(
                flightDateTime.toLocalDate().getDayOfMonth(),
                flightDateTime.getDayOfWeek().getValue(),
                flightDateTime,
                dto.opUniqueCarrier(),
                dto.origin(),
                dto.dest(),
                dto.distance(),
                crsDepTime
        );
    }
}














