package com.codeid.usersmanagement.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse<T> {

    @Builder.Default
    private Instant timestamp = Instant.now();

    private HttpStatus status;

    private Integer code;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private T errors;
}
