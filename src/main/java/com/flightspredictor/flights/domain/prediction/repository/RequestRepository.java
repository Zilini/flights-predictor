package com.flightspredictor.flights.domain.prediction.repository;

import com.flightspredictor.flights.domain.prediction.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {

    Optional<Request> findByFlightDateTimeAndOpUniqueCarrierAndOriginAndDestAndDistance(
            OffsetDateTime flightDateTime,
            String opUniqueCarrier,
            String origin,
            String dest,
            Double distance
    );
}
