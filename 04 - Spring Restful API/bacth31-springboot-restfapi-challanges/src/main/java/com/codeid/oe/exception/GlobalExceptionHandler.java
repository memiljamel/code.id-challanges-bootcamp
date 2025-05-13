package com.codeid.oe.exception;

import com.codeid.oe.model.response.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.Collections;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ApiError> handleMaxSizeException(MaxUploadSizeExceededException ex) {
        ApiError error = new ApiError(
                HttpStatus.PAYLOAD_TOO_LARGE,
                "File Too Large",
                Collections.singletonList(ex.getMessage()));

        return new ResponseEntity<>(error, HttpStatus.PAYLOAD_TOO_LARGE);
    }
}