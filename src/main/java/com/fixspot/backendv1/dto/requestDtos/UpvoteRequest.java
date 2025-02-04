package com.fixspot.backendv1.dto.requestDtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpvoteRequest {
    private Integer userId;
    private Integer issueId;
}
