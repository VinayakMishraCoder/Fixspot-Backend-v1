package com.fixspot.backendv1.service.reporter;

import com.fixspot.backendv1.enums.IssueStatus;
import com.fixspot.backendv1.dto.requestDtos.CreateIssueRequest;
import com.fixspot.backendv1.entities.IssueEntity;
import com.fixspot.backendv1.entities.UserEntity;
import com.fixspot.backendv1.generalUtil.ResultWrapper;
import com.fixspot.backendv1.repositories.IssueRepository;
import com.fixspot.backendv1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReporterServiceImpl implements ReporterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IssueRepository issueRepository;

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
                .issuer(user)
                .build();

        issueRepository.save(issue);

        return ResponseEntity.ok(ResultWrapper.success("Successful","Saved."));
    }
}
