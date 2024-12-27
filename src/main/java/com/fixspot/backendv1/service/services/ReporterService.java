package com.fixspot.backendv1.service.services;

import com.fixspot.backendv1.dto.requestDtos.CreateIssueRequest;
import com.fixspot.backendv1.generalUtil.ResultWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ReporterService {

    ResponseEntity<ResultWrapper<String>> addIssueToReporter(CreateIssueRequest request);
}
