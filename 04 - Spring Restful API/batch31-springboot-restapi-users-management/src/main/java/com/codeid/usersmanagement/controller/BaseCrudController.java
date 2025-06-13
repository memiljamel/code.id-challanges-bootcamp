package com.codeid.usersmanagement.controller;

import com.codeid.usersmanagement.model.response.ApiResponse;
import com.codeid.usersmanagement.service.BaseCrudService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseCrudController<CreateRequest, UpdateRequest, Response, ID> {

    protected abstract BaseCrudService<CreateRequest, UpdateRequest, Response, ID> getService();

    @GetMapping(
            path = "/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<List<Response>>> getAll(Pageable pageable) {
        List<Response> data = getService().findAll(pageable);

        ApiResponse<List<Response>> response = new ApiResponse<>(
                HttpStatus.OK,
                200,
                "Data retrieved successfully",
                data
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<Response>> getById(@PathVariable ID id) {
        Response data = getService().findById(id);

        ApiResponse<Response> response = new ApiResponse<>(
                HttpStatus.OK,
                200,
                "Data retrieved successfully by ID",
                data
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(
            path = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<Response>> create(@RequestBody @Valid CreateRequest request) {
        Response data = getService().save(request);

        ApiResponse<Response> response = new ApiResponse<>(
                HttpStatus.CREATED,
                201,
                "Data created successfully",
                data
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<Response>> update(@PathVariable ID id, @RequestBody @Valid UpdateRequest request) {
        Response data = getService().update(id, request);

        ApiResponse<Response> response = new ApiResponse<>(
                HttpStatus.OK,
                200,
                "Data updated successfully",
                data
        );

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