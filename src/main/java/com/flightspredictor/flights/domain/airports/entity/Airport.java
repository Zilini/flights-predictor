package com.flightspredictor.flights.domain.airports.entity;

import com.flightspredictor.flights.domain.airports.dto.AirportData;
import com.flightspredictor.flights.domain.airports.util.GoogleMapsUrlBuilder;
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

    // Abreviasión de tres letras asignadas por la Asociación Internacional de Transporte Aéreo
    @Column(name = "airport_iata", nullable = false, length = 3)
    private String airportIata;

    @Column(name = "airport_name")
    private String airportName;

    @Column(name = "country")
    private String country;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "longitude",nullable = false)
    private Float longitude;

    @Column(name = "latitude", nullable = false)
    private Float latitude;

    // Altura en metros sobre el nivel del mar (msnm)
    @Column(name = "elevation")
    private Double elevation;

    // Zona horaria (America/Lima)
    @Column(name = "time_zone", nullable = false)
    private String timeZone;

    // Enlace directo a la ubicación del aeropuerto en google maps
    @Column(name = "google_maps", nullable = false)
    private String googleMaps;

    public Airport(AirportData data) {
        this.id = null;
        this.airportIata = data.airportIata();
        this.airportName = data.airportName();
        this.country = data.country();
        this.cityName = data.cityName();
        this.longitude = data.longitude();
        this.latitude = data.latitude();
        this.elevation = data.elevation();
        this.timeZone = data.timeZone();
        this.googleMaps = GoogleMapsUrlBuilder.buildGoogleMapURl(latitude, longitude);
    }
}
