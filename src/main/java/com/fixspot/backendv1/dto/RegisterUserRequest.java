package com.fixspot.backendv1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterUserRequest {
    private String username;
    private String password;
    private String role;
}

