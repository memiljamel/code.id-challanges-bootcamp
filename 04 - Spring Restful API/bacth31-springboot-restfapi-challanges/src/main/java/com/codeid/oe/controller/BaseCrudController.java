package com.codeid.oe.controller;

import com.codeid.oe.model.enumeration.StatusEnum;
import com.codeid.oe.model.response.ApiResponse;
import com.codeid.oe.service.BaseCrudService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseCrudController<T, ID> {

    protected abstract BaseCrudService<T, ID> getService();

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<T>>> getAll() {
        ApiResponse<List<T>> response = new ApiResponse<>(
                StatusEnum.Success,
                "Data retrieved successfully",
                getService().findAll()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<T>> getById(@PathVariable ID id) {
        ApiResponse<T> response = new ApiResponse<>(
                StatusEnum.Success,
                "Data retrieved successfully by ID",
                getService().findById(id)
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse<T>> create(@RequestBody @Valid T entity) {
        ApiResponse<T> response = new ApiResponse<>(
                StatusEnum.Success,
                "Data created successfully",
                getService().save(entity)
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<T>> update(@PathVariable ID id, @RequestBody @Valid T entity) {
        ApiResponse<T> response = new ApiResponse<>(
                StatusEnum.Success,
                "Data updated successfully",
                getService().update(id, entity)
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        getService().delete(id);

        return ResponseEntity.noContent().build();
    }
}
