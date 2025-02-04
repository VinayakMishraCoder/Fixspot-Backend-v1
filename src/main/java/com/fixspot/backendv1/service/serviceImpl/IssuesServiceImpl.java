package com.fixspot.backendv1.service.serviceImpl;

import com.fixspot.backendv1.dto.requestDtos.UpvoteRequest;
import com.fixspot.backendv1.entities.IssueEntity;
import com.fixspot.backendv1.entities.UserEntity;
import com.fixspot.backendv1.generalUtil.ResultWrapper;
import com.fixspot.backendv1.repositories.IssueRepository;
import com.fixspot.backendv1.repositories.UserRepository;
import com.fixspot.backendv1.service.services.IssuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class IssuesServiceImpl implements IssuesService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<ResultWrapper<String>> upvoteIssue(UpvoteRequest upvoteRequest) {
        Optional<IssueEntity> issueOptional = issueRepository.findById(upvoteRequest.getIssueId());
        Optional<UserEntity> userOptional = userRepository.findById(upvoteRequest.getUserId());

//        if(issue.isEmpty() || user.isEmpty()) throw new RuntimeException("Failed fetching provided user or issue.");

//        Optional<UserEntity> userOptional = userRepository.findById(request.getUserId());
//        Optional<IssueEntity> issueOptional = issueRepository.findById(request.getIssueId());

        if (userOptional.isPresent() && issueOptional.isPresent()) {
            UserEntity user = userOptional.get();
            IssueEntity issue = issueOptional.get();

            // Add user to issue's upvoters
            issue.getUpVoters().add(user);

            // Add issue to user's upvoted issues
            user.getUpvotedIssues().add(issue);

            // Save the updated entities
            issueRepository.save(issue);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Failed fetching provided user or issue.");
        }

        return ResponseEntity.ok(ResultWrapper.success("success", "Liked successfully"));
    }
}
