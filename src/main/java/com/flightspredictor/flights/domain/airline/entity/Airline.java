package com.flightspredictor.flights.domain.airline.entity;

import com.flightspredictor.flights.domain.airline.domain.AirlineResponse;
import com.flightspredictor.flights.domain.airline.dto.AirlineData;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "airline")
@Entity(name = "Airline")

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "op_unique_carrier")
    String opUniqueCarrier;

    @Setter
    @Column(name = "airline_name")
    String airlineName;

    public Airline(AirlineData data) {
        this.id = null;
        this.opUniqueCarrier = data.opUniqueCarrier();
        this.airlineName = data.airlineName();
    }

}
