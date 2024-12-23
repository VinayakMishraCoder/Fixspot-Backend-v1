package com.fixspot.backendv1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    public String username;
    public String password;
}

