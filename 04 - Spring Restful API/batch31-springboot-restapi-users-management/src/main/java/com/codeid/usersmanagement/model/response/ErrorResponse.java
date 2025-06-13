package com.codeid.usersmanagement.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.Instant;

public record ErrorResponse<T>(
        Instant timestamp,
        HttpStatus status,
        Integer code,
        String message,

        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        T errors
) {
    public ErrorResponse(HttpStatus status, Integer code, String message, T errors) {
        this(Instant.now(), status, code, message, errors);
    }

    public ErrorResponse(HttpStatus status, Integer code, String message) {
        this(Instant.now(), status, code, message, null);
    }
}
