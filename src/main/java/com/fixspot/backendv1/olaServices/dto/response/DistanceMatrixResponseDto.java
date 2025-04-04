package com.fixspot.backendv1.olaServices.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class DistanceMatrixResponseDto {
    private List<RowDto> rows;
    private String status;
}
