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

public abstract class BaseCrudController<CreateReq, UpdateReq extends UpdateIdRequest<ID>, Res, ID> {

    protected abstract BaseCrudService<CreateReq, UpdateReq, Res, ID> getService();

    @GetMapping(
            path = "/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<List<Res>>> getAll(Pageable pageable) {
        List<Res> data = getService().findAll(pageable);

        ApiResponse<List<Res>> response = ApiResponse.<List<Res>>builder()
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
    public ResponseEntity<ApiResponse<Res>> getById(@PathVariable ID id) {
        Res data = getService().findById(id);

        ApiResponse<Res> response = ApiResponse.<Res>builder()
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
    public ResponseEntity<ApiResponse<Res>> create(@RequestBody @Valid CreateReq request) {
        Res data = getService().save(request);

        ApiResponse<Res> response = ApiResponse.<Res>builder()
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
    public ResponseEntity<ApiResponse<Res>> update(@PathVariable ID id, @RequestBody @Valid UpdateReq request) {
        request.setId(id);

        Res data = getService().update(request);

        ApiResponse<Res> response = ApiResponse.<Res>builder()
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