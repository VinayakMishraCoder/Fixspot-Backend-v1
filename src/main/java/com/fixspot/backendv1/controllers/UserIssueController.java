package com.fixspot.backendv1.controllers;

import com.fixspot.backendv1.dto.requestDtos.CreateIssueRequest;
import com.fixspot.backendv1.dto.responseDtos.ReporterIssueResponse;
import com.fixspot.backendv1.generalUtil.ResultWrapper;
import com.fixspot.backendv1.generalUtil.Routes;
import com.fixspot.backendv1.service.issue.IssueService;
import com.fixspot.backendv1.service.reporter.ReporterService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.API_V1)
public class UserIssueController {

    @Autowired
    private IssueService issueService;

    @Autowired
    private ReporterService reporterService;

    @PostMapping(Routes.ISSUE_CREATE)
    public ResponseEntity<ResultWrapper<String>> addIssueToUser(@RequestBody CreateIssueRequest issue) {
        return reporterService.addIssueToReporter(issue);
    }

    @PostMapping(Routes.ISSUES_GET)
    public ResponseEntity<ResultWrapper<List<ReporterIssueResponse>>> getReporterIssues(@PathVariable Integer userId) {
        return reporterService.getReporterIssues(userId);
    }

    @PostMapping(Routes.ISSUE_UPVOTE)
    public ResponseEntity<ResultWrapper<String>> upvoteIssue(@PathVariable Integer issueId, @PathVariable Integer userId) {
        return issueService.upvoteIssue(userId, issueId);
    }

    @DeleteMapping(Routes.ISSUE_DOWN_VOTE)
    public ResponseEntity<ResultWrapper<String>> removeUpvote(@PathVariable Integer issueId, @PathVariable Integer userId) {
        return issueService.removeUpvote(userId, issueId);
    }
}



