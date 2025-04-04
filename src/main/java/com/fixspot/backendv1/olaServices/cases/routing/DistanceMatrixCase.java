package com.fixspot.backendv1.olaServices.cases.routing;

import com.fixspot.backendv1.olaServices.dto.request.DistanceMatrixRequestDto;
import com.fixspot.backendv1.olaServices.dto.response.DistanceMatrixResponseDto;
import com.fixspot.backendv1.olaServices.restClient.OlaApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistanceMatrixCase {

    @Autowired
    private OlaApiClient olaApiClient;

    public DistanceMatrixResponseDto fetchDistanceMatrix(String origins, String destinations, String mode) {
        DistanceMatrixRequestDto requestDto = new DistanceMatrixRequestDto(origins, destinations, mode);
        return olaApiClient.getDistanceMatrix(requestDto);
    }
}
