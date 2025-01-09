package com.fixspot.backendv1.service.services;

import com.fixspot.backendv1.dto.requestDtos.UpvoteRequest;
import com.fixspot.backendv1.generalUtil.ResultWrapper;
import org.springframework.http.ResponseEntity;

public interface IssuesService {
    ResponseEntity<ResultWrapper<String>> upvoteIssue(UpvoteRequest upvoteRequest);
}
