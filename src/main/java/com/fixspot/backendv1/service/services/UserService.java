package com.fixspot.backendv1.service.services;

import com.fixspot.backendv1.dto.common.RegisterUserRequest;
import com.fixspot.backendv1.dto.responseDtos.ReporterDetailsResponse;
import com.fixspot.backendv1.entities.UserEntity;
import com.fixspot.backendv1.exception.exceptions.UserExistsException;
import com.fixspot.backendv1.generalUtil.ResultWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    ResponseEntity<ResultWrapper<ReporterDetailsResponse>> getUser(String username);
    ResponseEntity<ResultWrapper<UserEntity>> registerUser(RegisterUserRequest user) throws UserExistsException, Exception;
}
