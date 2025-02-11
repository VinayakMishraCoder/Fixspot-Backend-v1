package com.fixspot.backendv1.service.reporter;

import com.fixspot.backendv1.dto.requestDtos.CreateIssueRequest;
import com.fixspot.backendv1.dto.responseDtos.ReporterIssueResponse;
import com.fixspot.backendv1.generalUtil.ResultWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReporterService {
    ResponseEntity<ResultWrapper<String>> addIssueToReporter(CreateIssueRequest request);
    ResponseEntity<ResultWrapper<List<ReporterIssueResponse>>> getReporterIssues(Integer userId);
}
