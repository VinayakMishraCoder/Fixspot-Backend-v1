package com.fixspot.backendv1.service.issue;

import com.fixspot.backendv1.generalUtil.ResultWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface IssueService {
    ResponseEntity<ResultWrapper<String>> upvoteIssue(Integer userId, Integer issueId);
    ResponseEntity<ResultWrapper<String>> removeUpvote(Integer userId, Integer issueId);
}