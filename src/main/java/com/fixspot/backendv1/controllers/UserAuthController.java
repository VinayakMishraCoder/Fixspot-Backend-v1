package com.fixspot.backendv1.controllers;

import com.fixspot.backendv1.auth.CustomUserDetailServiceImpl;
import com.fixspot.backendv1.auth.JwtService;
import com.fixspot.backendv1.dto.requestDtos.RegisterUserRequest;
import com.fixspot.backendv1.generalUtil.Routes;
import com.fixspot.backendv1.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.API_V1)
public class UserAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private CustomUserDetailServiceImpl customUserDetailServiceImpl;
    @Autowired
    private UserService userService;

    @PostMapping(Routes.AUTH_LOGIN)
    public String authenticateAndGetToken(
            @RequestBody RegisterUserRequest loginForm
    ) throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginForm.getUsername(), loginForm.getPassword()
        ));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(customUserDetailServiceImpl.loadUserByUsername(loginForm.getUsername()));
        } else {
            throw new UsernameNotFoundException("Invalid credentials");
        }
    }
}


