package com.flightspredictor.flights.domain.prediction.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ModelPredictionRequest(

        @JsonProperty("fl_date")
        String flightDateTime,

        @JsonProperty("op_unique_carrier")
        String opUniqueCarrier,

        @JsonProperty("origin")
        String origin,

        @JsonProperty("dest")
        String dest,

        @JsonProperty("distance")
        Double distance,

        @JsonProperty("crs_dep_time")
        Integer crsDepTime,

        @JsonProperty("crs_elapsed_time")
        Integer crsElapsedTime,

        @JsonProperty("origin_weather_tavg")
        Double originTavg,

        @JsonProperty("origin_weather_prcp")
        Double originPrcp,

        @JsonProperty("origin_weather_wspd")
        Double originWspd,

        @JsonProperty("origin_weather_pres")
        Double originPres,

        @JsonProperty("dest_weather_tavg")
        Double destTavg,

        @JsonProperty("dest_weather_prcp")
        Double destPrcp,

        @JsonProperty("dest_weather_wspd")
        Double destWspd,

        @JsonProperty("dest_weather_pres")
        Double destPres
) {}
