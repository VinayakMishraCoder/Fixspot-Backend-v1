package com.fixspot.backendv1.dto.responseDtos;

import com.fixspot.backendv1.entities.UserEntity;

import java.util.List;

public class ReporterIssue {

    private Integer id;
    private String issueDescription;
    private List<String> images;
    private UserEntity issuer;
    private String longitude;
    private String latitude;
    private String address;
    private String upVoters; // Users who up-voted this issue.
    private String status; // will be from the enum IssueStatus
    private Boolean isUpVoted;
}
