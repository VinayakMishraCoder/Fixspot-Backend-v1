package com.fixspot.backendv1.dto.requestDtos;

import lombok.Data;

import java.util.List;

@Data
public class CreateIssueRequest {
    private String issueDescription;
    private Integer reporterId;
    private String longitude;
    private String latitude;
    private String address;
    private List<String> imgUrls;
}
