package com.flightspredictor.flights.domain.airline.controller;

import com.flightspredictor.flights.domain.airline.dto.AirlineResp;
import com.flightspredictor.flights.domain.airline.repository.AirlineRepository;
import com.flightspredictor.flights.domain.airline.service.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/airlines")
@RestController
@RequiredArgsConstructor
public class AirlineController {

    private final AirlineRepository airlineRepository;
    private final AirlineService airlineService;

    @GetMapping("/{opUniqueCarrier}")
    public ResponseEntity<AirlineResp> getAirline (@PathVariable
                                                   String opUniqueCarrier) {
        var airline = airlineService.getAirline(opUniqueCarrier);

        return ResponseEntity.ok(new AirlineResp(airline));
    }
}
