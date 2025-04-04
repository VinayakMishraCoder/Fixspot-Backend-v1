package com.fixspot.backendv1.olaServices.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDto {
    private String reason;
    private String request_type;
    private String request_id;
    private String status;
}
