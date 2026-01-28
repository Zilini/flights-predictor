package com.flightspredictor.flights.domain.airline.client;

import com.flightspredictor.flights.domain.airline.domain.AirlineResponse;
import com.flightspredictor.flights.domain.airline.util.AirlineUrlBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
@RequiredArgsConstructor
public class AirlineApiClient {

    private final RestTemplate restTemplate;

    @Value("${api.market.key:}")
    private String apiKey;

    public AirlineResponse airlineResponse (String opUniqueCarrier) {

        String url = AirlineUrlBuilder.buildAirlineUrl(opUniqueCarrier);

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-market-key", apiKey);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.GET, entity, AirlineResponse.class).getBody();
    }
}
