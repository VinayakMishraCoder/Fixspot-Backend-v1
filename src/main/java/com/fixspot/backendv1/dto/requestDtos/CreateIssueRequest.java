package com.fixspot.backendv1.dto.requestDtos;


import lombok.Data;

@Data
public class CreateIssueRequest {

    private String issueDescription;
    private Integer reporterId;
    private String longitude;
    private String latitude;
    private String address;
}
