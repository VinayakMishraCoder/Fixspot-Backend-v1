package com.fixspot.backendv1.service.reporter;

import com.fixspot.backendv1.dto.responseDtos.ReporterIssueResponse;
import com.fixspot.backendv1.enums.IssueStatus;
import com.fixspot.backendv1.dto.requestDtos.CreateIssueRequest;
import com.fixspot.backendv1.entities.IssueEntity;
import com.fixspot.backendv1.entities.UserEntity;
import com.fixspot.backendv1.generalUtil.Logger;
import com.fixspot.backendv1.generalUtil.ResultWrapper;
import com.fixspot.backendv1.repositories.IssueRepository;
import com.fixspot.backendv1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ReporterServiceImpl implements ReporterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Override
    public ResponseEntity<ResultWrapper<String>> addIssueToReporter(CreateIssueRequest request) {
        Optional<UserEntity> userOptional = userRepository.findById(request.getReporterId());
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with id: " + request.getReporterId());
        }

        UserEntity user = userOptional.get();

        IssueEntity issue = IssueEntity.builder()
                .issueDescription(request.getIssueDescription())
                .latitude(request.getLatitude())
                .address(request.getAddress())
                .longitude(request.getLongitude())
                .status(IssueStatus.UNRESOLVED.name())
                .imgUrls(request.getImgUrls())
                .issuer(user)
                .build();

        issueRepository.save(issue); // Save issue to generate ID

        return ResponseEntity.ok(ResultWrapper.success("Successful", "Issue saved with images."));
    }

    @Override
    public ResponseEntity<ResultWrapper<List<ReporterIssueResponse>>> getReporterIssues(Integer userId) {
        List<IssueEntity> issues = issueRepository.findByIssuer(userId);
        Logger.debug(String.valueOf(issues.size()));
        List<ReporterIssueResponse> reporterIssues = new ArrayList<>(Collections.emptyList());
        issues.forEach((issue -> {
            List<UserEntity> upvoters = issue.getUpvoters();
            reporterIssues.add(
                    ReporterIssueResponse.builder()
                            .id(issue.getId())
                            .issueDescription(issue.getIssueDescription())
                            .issuer(issue.getIssuer() != null ? issue.getIssuer().getId() : null) // Null check for issuer
                            .longitude(issue.getLongitude())
                            .images(issue.getImgUrls())
                            .latitude(issue.getLatitude())
                            .address(issue.getAddress())
                            .upVoters(String.valueOf(upvoters.size())) // Avoid null
                            .status(issue.getStatus())
                            .isUpVoted(upvoters.stream().anyMatch(user -> user.getId().equals(userId)))
                            .build()
            );
        }));

        return ResponseEntity.ok(ResultWrapper.success("success", reporterIssues));
    }
}
