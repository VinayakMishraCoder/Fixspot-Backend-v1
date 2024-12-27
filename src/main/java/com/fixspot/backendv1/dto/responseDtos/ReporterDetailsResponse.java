package com.fixspot.backendv1.dto.responseDtos;

import com.fixspot.backendv1.entities.IssueEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ReporterDetailsResponse {
    private Integer id;
    private String username;
    private String longitude;
    private String latitude;
    private String houseNo;
    private String area;
    private String city;
    private String pinCode;
    private String landmark;
    private String mobileNo;
    private String firstName;
    private String lastName;
    private String role;
    private List<ReporterIssue> reportedIssues; // Issues reported by this user.
}
