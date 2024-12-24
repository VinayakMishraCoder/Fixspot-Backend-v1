package com.fixspot.backendv1.exception.handler;

import com.fixspot.backendv1.exception.exceptions.UserExistsException;
import com.fixspot.backendv1.generalUtil.ResultWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<ResultWrapper<Object>> handleUserExistsException(UserExistsException ex) {
        return ResponseEntity.ok(ResultWrapper.failure(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResultWrapper<Object>> handleGeneralException(Exception ex) {
        return ResponseEntity.ok(ResultWrapper.failure("An unexpected error occurred: " +ex.getMessage()));
    }
}

/*
* Use Runtime when just passing a statement for error back to UI (when these errors are related to business logic), they are non-recoverable.

* Non-Recoverable Errors
* In contrast, non-recoverable errors are critical issues that usually result from programming bugs, system failures, or logic errors,
* and the application typically cannot continue running meaningfully. Examples include:
*
* 1. Null pointer dereferences (NullPointerException).
* 2. Divide by zero (ArithmeticException).
* 3. Misconfigured environment variables.
* These should be avoided through proper programming practices and validation, not caught at runtime.
*
* Recoverable Errors:
* For recoverable situations like file not found, invalid input, or database connection issues, use checked exceptions instead, allowing the caller to handle them.
* */
