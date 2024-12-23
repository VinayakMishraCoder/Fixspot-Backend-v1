package com.fixspot.backendv1.generalUtil;


public class ResultWrapper<T> {

    public boolean success;

    public String message; // Error message or success message

    public T data; // Actual response data

    public ResultWrapper() {}

    // Manually add the required constructor
    public ResultWrapper(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // Static Factory Methods
    public static <T> ResultWrapper<T> success(String message, T data) {
        return new ResultWrapper<T>(true, message, data);
    }

    public static <T> ResultWrapper<T> failure(String message) {
        return new ResultWrapper<T>(false, message, null);
    }
}
