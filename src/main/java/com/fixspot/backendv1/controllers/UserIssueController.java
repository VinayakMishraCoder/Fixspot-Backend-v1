package com.fixspot.backendv1.controllers;

import com.fixspot.backendv1.dto.requestDtos.CreateIssueRequest;
import com.fixspot.backendv1.generalUtil.ResultWrapper;
import com.fixspot.backendv1.generalUtil.Routes;
import com.fixspot.backendv1.service.services.ReporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.API_V1)
public class UserIssueController {

    @Autowired
    private ReporterService reporterService;

    @PostMapping(Routes.ISSUE_CREATE)
    public ResponseEntity<ResultWrapper<String>> addIssueToUser(
            @RequestBody CreateIssueRequest issue
    ) {
        return reporterService.addIssueToReporter(issue);
    }

    @PostMapping(Routes.ISSUE_UPVOTE)
    public ResponseEntity<ResultWrapper<String>> upvoteIssue(
            @RequestBody CreateIssueRequest issue
    ) {
        return reporterService.addIssueToReporter(issue);
    }


}



