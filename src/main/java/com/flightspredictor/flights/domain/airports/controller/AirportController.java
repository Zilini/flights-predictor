package com.flightspredictor.flights.domain.airports.controller;

import com.flightspredictor.flights.domain.airports.dto.AirportResp;
import com.flightspredictor.flights.domain.airports.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/airports")
@RestController
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @GetMapping("/{iata}")
    public ResponseEntity<AirportResp> getAirport (@PathVariable String iata) {
        var airport = airportService.getAirport(iata);

        return ResponseEntity.ok(new AirportResp(airport));
    }
}
