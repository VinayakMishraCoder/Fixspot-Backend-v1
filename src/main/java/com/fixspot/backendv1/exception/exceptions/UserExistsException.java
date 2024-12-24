package com.fixspot.backendv1.exception.exceptions;

public class UserExistsException extends RuntimeException {
    public UserExistsException() {
        super("User Already Exists");
    }
    public UserExistsException(String message) {
        super(message);
    }
}
