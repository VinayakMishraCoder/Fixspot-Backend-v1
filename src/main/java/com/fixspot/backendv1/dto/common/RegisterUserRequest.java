package com.fixspot.backendv1.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterUserRequest {
    private String username;
    private String password;
    private String role;
}

