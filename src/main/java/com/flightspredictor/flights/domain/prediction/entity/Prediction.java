package com.flightspredictor.flights.domain.prediction.entity;

import com.flightspredictor.flights.domain.prediction.enums.Prevision;
import com.flightspredictor.flights.domain.prediction.enums.Status;
import com.flightspredictor.flights.domain.requests.dto.ModelPredictionResponse;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "response")
@Entity(name = "Prediction")

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Prevision prevision;

    private Double probability;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    private Request request;

    @Column(name = "resultado_real")
    private String resultadoReal;

    public Prediction(ModelPredictionResponse data) {
        if ("Retrasado".equalsIgnoreCase(data.prevision())) {
            this.prevision = Prevision.DELAYED;
        } else {
            this.prevision = Prevision.ON_TIME;
        }
        this.status = Status.PROCESSED;
    }

    public void setResultadoReal(String resultadoReal) {
    this.resultadoReal = resultadoReal;
}
    public void setRequest(Request request) {
    this.request = request;
}
}

