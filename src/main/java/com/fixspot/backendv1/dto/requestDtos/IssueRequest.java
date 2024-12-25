package com.fixspot.backendv1.dto.requestDtos;


import lombok.Builder;
import lombok.Data;

@Data
public class IssueRequest {
    private String issueId;
    private String userId;
    private String description;
    private String status;
}
