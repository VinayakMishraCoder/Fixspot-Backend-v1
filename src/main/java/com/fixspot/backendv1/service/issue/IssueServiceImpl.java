package com.fixspot.backendv1.service.issue;

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
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<ResultWrapper<String>> upvoteIssue(Integer userId, Integer issueId) {
        Optional<UserEntity> userOpt = userRepository.findById(userId);
        Optional<IssueEntity> issueOpt = issueRepository.findById(issueId);

        if (userOpt.isEmpty() || issueOpt.isEmpty()) {
            return ResponseEntity.ok(ResultWrapper.failure("User or Issue not found!"));
        }

        UserEntity user = userOpt.get();
        IssueEntity issue = issueOpt.get();

        if (issue.getUpvoters().contains(user)) {
            return ResponseEntity.ok(ResultWrapper.failure("User has already upvoted this issue!"));
        }

        issue.getUpvoters().add(user);
        issueRepository.save(issue);
        return ResponseEntity.ok(ResultWrapper.success("success","Issue upvoted successfully!"));
    }

    @Override
    public ResponseEntity<ResultWrapper<String>> removeUpvote(Integer userId, Integer issueId) {
        Optional<UserEntity> userOpt = userRepository.findById(userId);
        Optional<IssueEntity> issueOpt = issueRepository.findById(issueId);

        if (userOpt.isEmpty() || issueOpt.isEmpty()) {
            return ResponseEntity.ok(ResultWrapper.failure("User or Issue not found!"));
        }

        UserEntity user = userOpt.get();
        IssueEntity issue = issueOpt.get();

        if (!issue.getUpvoters().contains(user)) {
            return ResponseEntity.ok(ResultWrapper.failure("User has not upvoted this issue!"));
        }

        issue.getUpvoters().remove(user);
        issueRepository.save(issue);

        return ResponseEntity.ok(ResultWrapper.success("success","Issue down voted successfully!"));
    }
}
