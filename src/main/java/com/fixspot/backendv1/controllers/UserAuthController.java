package com.fixspot.backendv1.controllers;

import com.fixspot.backendv1.dto.common.RegisterUserRequest;
import com.fixspot.backendv1.generalUtil.ResultWrapper;
import com.fixspot.backendv1.generalUtil.Routes;
import com.fixspot.backendv1.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.API_V1)
public class UserAuthController {

    @Autowired
    private UserService userService;

    @PostMapping(Routes.AUTH_LOGIN)
    public ResponseEntity<ResultWrapper<String>> authenticateAndGetToken(@RequestBody RegisterUserRequest loginForm) {
           return userService.generateToken(loginForm);
    }
}


