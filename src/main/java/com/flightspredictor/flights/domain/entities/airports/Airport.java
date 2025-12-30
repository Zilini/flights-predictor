package com.flightspredictor.flights.domain.entities.airports;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "airports")
@Entity(name = "Airport")

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country_name;
    private String city_name;
    private String airport_name;
    private String airport_iata;
    private String city_iata;
    private Float longitude;
    private Float latitude;
    private String timezone;
}
