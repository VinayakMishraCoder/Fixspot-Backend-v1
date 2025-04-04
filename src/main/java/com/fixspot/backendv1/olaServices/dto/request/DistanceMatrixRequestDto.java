package com.fixspot.backendv1.olaServices.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistanceMatrixRequestDto {
    private String origins;
    private String destinations;
    private String mode = "driving";  // Default to driving
}
