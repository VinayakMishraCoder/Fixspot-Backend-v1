package com.fixspot.backendv1.controllers;

import com.fixspot.backendv1.dto.requestDtos.RegisterUserRequest;
import com.fixspot.backendv1.dto.requestDtos.UsernameRequest;
import com.fixspot.backendv1.entities.UserModel;
import com.fixspot.backendv1.generalUtil.ResultWrapper;
import com.fixspot.backendv1.generalUtil.Routes;
import com.fixspot.backendv1.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.API_V1)
public class AuthorityController {

    @Autowired
    private UserService userService;

    @PostMapping(value = Routes.AUTHORITY_GET_USER)
    public ResponseEntity<ResultWrapper<UserModel>> getUser(
            @RequestBody UsernameRequest usernameRequest
    ) {
        return userService.getUser(usernameRequest.getUsername());
    }

    @PostMapping(value = Routes.AUTHORITY_REGISTRATION)
    public ResponseEntity<ResultWrapper<UserModel>> saveUser(
            @RequestBody RegisterUserRequest user
    ) throws Exception {
        return userService.registerUser(user);
    }
}
