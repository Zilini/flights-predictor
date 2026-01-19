package com.flightspredictor.flights.domain.prediction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.flightspredictor.flights.domain.requests.dto.ModelPredictionRequest;

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
    private LocalDate flightDateTime;

    @Column(name = "op_unique_carrier", nullable = false)
    private String opUniqueCarrier;

    @Column(name = "origin", length = 3, nullable = false)
    private String origin;

    @Column(name = "dest", length = 3, nullable = false)
    private String dest;

    @Column(name = "distance", nullable = false)
    private Double distance;

    @OneToOne(mappedBy = "request", fetch = FetchType.LAZY)
    private Prediction prediction;

    public Request(ModelPredictionRequest data) {
        this.id = null;
        this.opUniqueCarrier = data.op_unique_carrier();
        this.origin = data.origin();
        this.dest = data.dest();
        this.distance = data.distance();
        this.prediction = new Prediction();
    }

}
