package com.fixspot.backendv1.service.services;

import com.fixspot.backendv1.dto.UserDto;
import com.fixspot.backendv1.entities.User;
import com.fixspot.backendv1.generalUtil.ResultWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    ResponseEntity<ResultWrapper<User>> getUser(String username);
    ResponseEntity<ResultWrapper<User>> saveUser(UserDto user);
}
