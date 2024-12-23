package com.fixspot.backendv1.controllers;

import com.fixspot.backendv1.generalUtil.ExceptionHandler;
import com.fixspot.backendv1.generalUtil.ResultWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserAuthController {

    @GetMapping(value = "get-user")
    public ResponseEntity<ResultWrapper<Integer>> getUser() {
        return ResponseEntity.ok(ResultWrapper.success("", 1));
    }
}


