package com.fixspot.backendv1.dto.common;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterUserRequest {
    private String username;
    private String password;
    private String role;
    private String longitude;
    private String latitude;
    private String houseNo;
    private String area;
    private String city;
    private String pinCode;
    private String landmark;
    private String mobileNo;
    private String firstName;
    private String lastName;
}

