package com.codeid.eshopay.exception;

import com.codeid.eshopay.model.response.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse<String>> handleMaxSizeException(MaxUploadSizeExceededException ex) {
        ErrorResponse<String> response = ErrorResponse.<String>builder()
                .status(HttpStatus.PAYLOAD_TOO_LARGE)
                .code(413)
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.PAYLOAD_TOO_LARGE);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse<String>> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErrorResponse<String> response = ErrorResponse.<String>builder()
                .status(HttpStatus.NOT_FOUND)
                .code(404)
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse<Map<String, List<String>>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())
                ));

        ErrorResponse<Map<String, List<String>>> response = ErrorResponse.<Map<String, List<String>>>builder()
                .status(HttpStatus.BAD_REQUEST)
                .code(400)
                .message("Validation failed")
                .errors(errors)
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse<String>> handleGlobalException(Exception ex) {
        ErrorResponse<String> response = ErrorResponse.<String>builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .code(500)
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
