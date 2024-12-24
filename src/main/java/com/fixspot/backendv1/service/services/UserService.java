package com.fixspot.backendv1.service.services;

import com.fixspot.backendv1.dto.requestDtos.RegisterUserRequest;
import com.fixspot.backendv1.entities.UserModel;
import com.fixspot.backendv1.exception.exceptions.UserExistsException;
import com.fixspot.backendv1.generalUtil.ResultWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    ResponseEntity<ResultWrapper<UserModel>> getUser(String username);
    ResponseEntity<ResultWrapper<UserModel>> registerUser(RegisterUserRequest user) throws UserExistsException, Exception;
}
