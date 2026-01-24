package com.flightspredictor.flights.domain.prediction.service;

import com.flightspredictor.flights.domain.prediction.client.PredictionApiClient;
import com.flightspredictor.flights.domain.prediction.dto.ModelPredictionRequest;
import com.flightspredictor.flights.domain.prediction.dto.ModelPredictionResponse;

import java.util.Optional;

import com.flightspredictor.flights.domain.prediction.dto.PredictionRequest;
import com.flightspredictor.flights.domain.prediction.dto.PredictionResponse;
import com.flightspredictor.flights.domain.prediction.entity.Prediction;
import com.flightspredictor.flights.domain.prediction.entity.Request;
import com.flightspredictor.flights.domain.prediction.mapper.PredictionMapper;
import com.flightspredictor.flights.domain.prediction.mapper.RequestMapper;
import com.flightspredictor.flights.domain.prediction.repository.RequestRepository;
import com.flightspredictor.flights.domain.prediction.util.GeoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PredictionService {

    private final AirportLookupService airportLookupService;
    private final PredictionApiClient predictionClient;
    private final PredictionMapper predictionMapper;
    private final RequestMapper requestMapper;
    private final RequestRepository requestRepo;

    public ModelPredictionResponse predict(PredictionRequest request){

        // Busca los aeropueros desde la request para guardarlos en base de datos si no existen
        airportLookupService.getAirportExist(
                request.origin(),
                request.dest()
        );

        // Trae los aeropuertos y sus coordenadas
        var originAirport = airportLookupService.getAirport(request.origin());
        var destAirport = airportLookupService.getAirport(request.dest());

        // Calcula distancia automáticamente la distancia para inyectarla en la request
        double calculatedDistance = GeoUtils.calculateDistance(
                originAirport.get().getLongitude(), originAirport.get().getLongitude(),
                destAirport.get().getLatitude(), destAirport.get().getLatitude()
        );

        // Busca si no existe una request en la base de datos
        Optional<Request> existingRequest =
                requestRepo.
                        findByFlightDateTimeAndOpUniqueCarrierAndOriginAndDestAndDistance(
                                request.flightDateTime(),
                                request.opUniqueCarrier(),
                                request.origin(),
                                request.dest(),
                                calculatedDistance
            );

        // Si exite, devuelve la predicción asociada a request
        if (existingRequest.isPresent()) {
            Prediction prediction = existingRequest.get().getPrediction();

            return new ModelPredictionResponse(prediction);
        }

        // Mapea la request para entregarla al modelo
        ModelPredictionRequest requestModel = requestMapper.mapToModelRequest(request, calculatedDistance);

        //Si no exite, hace la llamada al modelo
        PredictionResponse response = predictionClient.predict(requestModel);

        // Traduce la respuesta del modelo con los enums (prevision, status)
        ModelPredictionResponse domainResponse = predictionMapper.mapToModelResponse(response);

        // Construye las entidades para ser almacenadas en la base de datos
        Request requestEntity = new Request(request, calculatedDistance);
        Prediction predictionEntity = new Prediction(domainResponse, requestEntity);

        // Hace la vinculación de la request con la predicción
        requestEntity.setPrediction(predictionEntity);

        // Guarda la request y a la vez la prediction asociada a ella
        requestRepo.save(requestEntity);

        return domainResponse;
    }
}














