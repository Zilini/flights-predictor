package com.flightspredictor.flights.domain.airline.repository;

import com.flightspredictor.flights.domain.airline.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
    Optional<Airline> findByOpUniqueCarrier(String opUniqueCarrier);

    /**
     * Verifica si existe una aerolinea con el código OP_CARRIER proporcionado.
     *
     * El método es interpretado automáticamente por Spring Data JPA
     * a partir de su nombre (Query Method).
     *
     * - existsBy → retorna true o false
     * - OpUniqueCarrier → campo de la entidad
     * - IgnoreCase → ignora mayúsculas y minúsculas
     *
     * @param opUniqueCarrier código de la aerolínea (2 letras)
     * @return true si la aerolínea existe en la base de datos,
     *         false en caso contrario
     */
    boolean existsByOpUniqueCarrierIgnoreCase(String opUniqueCarrier);
}
