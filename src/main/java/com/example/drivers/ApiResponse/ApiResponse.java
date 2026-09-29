package com.example.drivers.ApiResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
public class ApiResponse {
    private String message;
}