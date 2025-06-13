package com.codeid.usersmanagement.controller;

import com.codeid.usersmanagement.model.request.UpdateIdRequest;
import com.codeid.usersmanagement.model.response.ApiResponse;
import com.codeid.usersmanagement.service.BaseCrudService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseCrudController<CreateRequest, UpdateRequest extends UpdateIdRequest<ID>, Response, ID> {

    protected abstract BaseCrudService<CreateRequest, UpdateRequest, Response, ID> getService();

    @GetMapping(
            path = "/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<List<Response>>> getAll(Pageable pageable) {
        List<Response> data = getService().findAll(pageable);

        ApiResponse<List<Response>> response = ApiResponse.<List<Response>>builder()
                .status(HttpStatus.OK)
                .code(200)
                .message("Data retrieved successfully")
                .data(data)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<Response>> getById(@PathVariable ID id) {
        Response data = getService().findById(id);

        ApiResponse<Response> response = ApiResponse.<Response>builder()
                .status(HttpStatus.OK)
                .code(200)
                .message("Data retrieved successfully by ID")
                .data(data)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(
            path = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<Response>> create(@RequestBody @Valid CreateRequest request) {
        Response data = getService().save(request);

        ApiResponse<Response> response = ApiResponse.<Response>builder()
                .status(HttpStatus.CREATED)
                .code(201)
                .message("Data created successfully")
                .data(data)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<Response>> update(@PathVariable ID id, @RequestBody @Valid UpdateRequest request) {
        request.setId(id);

        Response data = getService().update(request);

        ApiResponse<Response> response = ApiResponse.<Response>builder()
                .status(HttpStatus.OK)
                .code(200)
                .message("Data updated successfully")
                .data(data)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        getService().delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}