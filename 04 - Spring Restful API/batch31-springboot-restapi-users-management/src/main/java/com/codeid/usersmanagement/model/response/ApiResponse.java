package com.codeid.usersmanagement.model.response;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public record ApiResponse<T>(
        Instant timestamp,
        HttpStatus status,
        Integer code,
        String message,
        T data
) {
    public ApiResponse(HttpStatus status, Integer code, String message, T data) {
        this(Instant.now(), status, code, message, data);
    }
}
