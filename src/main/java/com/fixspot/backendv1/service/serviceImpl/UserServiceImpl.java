package com.fixspot.backendv1.service.serviceImpl;

import com.fixspot.backendv1.dto.UserDto;
import com.fixspot.backendv1.dto.common.UserRoles;
import com.fixspot.backendv1.entities.UserModel;
import com.fixspot.backendv1.generalUtil.ResultWrapper;
import com.fixspot.backendv1.repositories.UserRepository;
import com.fixspot.backendv1.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<ResultWrapper<UserModel>> getUser(String username) {
        try {
            return ResponseEntity.ok(ResultWrapper.success("success", userRepository.findByUsername(username).get()));
        } catch (Exception e) {
            return ResponseEntity.ok(ResultWrapper.failure(e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<ResultWrapper<UserModel>> saveUser(UserDto user) {
        try {
            userRepository.save(UserModel.builder().password(user.getPassword()).username(user.getUsername()).role(user.getRole()).build());
            return ResponseEntity.ok(ResultWrapper.success("success", userRepository.findByUsername(user.getUsername()).get()));
        } catch (Exception e) {
            return ResponseEntity.ok(ResultWrapper.failure(e.getMessage()));
        }
    }
}
