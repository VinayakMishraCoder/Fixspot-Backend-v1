package com.fixspot.backendv1.controllers;

import com.fixspot.backendv1.auth.JwtService;
import com.fixspot.backendv1.auth.MyUserDetailService;
import com.fixspot.backendv1.dto.RegisterUserRequest;
import com.fixspot.backendv1.dto.UsernameRequest;
import com.fixspot.backendv1.entities.UserModel;
import com.fixspot.backendv1.generalUtil.ResultWrapper;
import com.fixspot.backendv1.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class UserAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "user/get-user")
    public ResponseEntity<ResultWrapper<UserModel>> getUser(@RequestBody UsernameRequest usernameRequest) {
        return userService.getUser(usernameRequest.getUsername());
    }

    @PostMapping(value = "home")
    public ResponseEntity<ResultWrapper<String>> getHome(@RequestBody UsernameRequest usernameRequest) {
        return ResponseEntity.ok(ResultWrapper.success("", "Data"));
    }

    @PostMapping(value = "user/register-user")
    public ResponseEntity<ResultWrapper<UserModel>> saveUser(@RequestBody RegisterUserRequest user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.registerUser(user);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody RegisterUserRequest loginForm) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginForm.getUsername(), loginForm.getPassword()
        ));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(myUserDetailService.loadUserByUsername(loginForm.getUsername()));
        } else {
            throw new UsernameNotFoundException("Invalid credentials");
        }
    }
}


