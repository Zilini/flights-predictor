package com.flightspredictor.flights.domain.prediction.entity;

import com.flightspredictor.flights.domain.prediction.dto.PredictionRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Table(name = "request")
@Entity(name = "Request")

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fl_date", nullable = false)
    private OffsetDateTime flightDateTime;

    @Column(name = "op_unique_carrier", nullable = false)
    private String opUniqueCarrier;

    @Column(name = "origin", length = 3, nullable = false)
    private String origin;

    @Column(name = "dest", length = 3, nullable = false)
    private String dest;

    @Column(name = "distance", nullable = false)
    private Double distance;

    @OneToOne(mappedBy = "request", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Prediction prediction;

    public Request(PredictionRequest data, Double calculatedDistance) {
        this.id = null;
        this.flightDateTime = data.flightDateTime();
        this.opUniqueCarrier = data.opUniqueCarrier();
        this.origin = data.origin();
        this.dest = data.dest();
        this.distance = calculatedDistance;
    }

    public void setPrediction(Prediction prediction){
        this.prediction = prediction;
    }

}
