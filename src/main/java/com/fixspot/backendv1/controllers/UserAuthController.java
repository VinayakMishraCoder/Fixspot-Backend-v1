package com.fixspot.backendv1.controllers;

import com.fixspot.backendv1.dto.UserDto;
import com.fixspot.backendv1.dto.UsernameRequest;
import com.fixspot.backendv1.entities.User;
import com.fixspot.backendv1.generalUtil.ExceptionHandler;
import com.fixspot.backendv1.generalUtil.ResultWrapper;
import com.fixspot.backendv1.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserAuthController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "get-user")
    public ResponseEntity<ResultWrapper<User>> getUser(@RequestBody UsernameRequest usernameRequest) {
        return userService.getUser(usernameRequest.username);
    }

    @PostMapping(value = "save-user")
    public ResponseEntity<ResultWrapper<User>> saveUser(@RequestBody UserDto user) {
        return userService.saveUser(user);
    }
}


