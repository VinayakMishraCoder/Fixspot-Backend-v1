package com.fixspot.backendv1.olaServices.restClient;

import com.fixspot.backendv1.olaServices.dto.response.DistanceMatrixResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fixspot.backendv1.olaServices.dto.request.DistanceMatrixRequestDto;

@Component
@RequiredArgsConstructor
public class OlaApiClient {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "https://api.ola.com/routing/v1/distanceMatrix";

    public DistanceMatrixResponseDto getDistanceMatrix(DistanceMatrixRequestDto requestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Request-Id", "some-uuid");  // Generate unique UUID in production
        headers.set("X-Correlation-Id", "some-uuid");

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        String url = BASE_URL + "?origins=" + requestDto.getOrigins() +
                "&destinations=" + requestDto.getDestinations() +
                "&mode=" + requestDto.getMode();

        ResponseEntity<DistanceMatrixResponseDto> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, DistanceMatrixResponseDto.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to fetch data from Ola API");
        }
    }
}
