package com.codeid.oe.model.response;

import com.codeid.oe.model.enumeration.StatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ApiResponse<T> {
    private StatusEnum status;
    private String message;
    private T data;
    private Instant timestamp;

    public ApiResponse(StatusEnum status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = Instant.now();
    }
}
