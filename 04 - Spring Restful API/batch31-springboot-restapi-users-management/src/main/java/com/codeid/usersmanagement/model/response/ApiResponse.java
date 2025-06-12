package com.codeid.usersmanagement.model.response;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    @Builder.Default
    private Instant timestamp = Instant.now();

    private HttpStatus status;

    private Integer code;

    private String message;

    private T data;
}
