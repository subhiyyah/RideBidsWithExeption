package com.example.drivers.ApiResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}