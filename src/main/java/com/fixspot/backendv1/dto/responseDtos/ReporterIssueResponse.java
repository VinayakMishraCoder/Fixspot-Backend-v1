package com.fixspot.backendv1.dto.responseDtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ReporterIssueResponse {
    private Integer id;
    private String issueDescription;
    private List<String> images;
    private Integer issuer;
    private String longitude;
    private String latitude;
    private String address;
    private String upVoters; // Number of users who up-voted this issue.
    private String status; // will be from the enum IssueStatus
    private Boolean isUpVoted;
}
