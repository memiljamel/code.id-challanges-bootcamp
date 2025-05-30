package com.codeid.eshopay.controller;

import com.codeid.eshopay.model.request.CreateCategoryRequest;
import com.codeid.eshopay.model.request.UpdateCategoryRequest;
import com.codeid.eshopay.model.response.ApiResponse;
import com.codeid.eshopay.model.response.CategoryResponse;
import com.codeid.eshopay.service.BaseCrudService;
import com.codeid.eshopay.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController extends BaseCrudController<
        CreateCategoryRequest,
        UpdateCategoryRequest,
        CategoryResponse,
        Short> {

    @Autowired
    private CategoryService categoryService;

    @Override
    protected BaseCrudService<CreateCategoryRequest, UpdateCategoryRequest, CategoryResponse, Short> getService() {
        return categoryService;
    }

    @PostMapping(
            path = "/",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<ApiResponse<CategoryResponse>> create(
            @ModelAttribute @Valid CreateCategoryRequest request
    ) {
        return super.create(request);
    }

    @PutMapping(
            path = "/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<ApiResponse<CategoryResponse>> update(
            Short id,
            @ModelAttribute @Valid UpdateCategoryRequest request
    ) {
        return super.update(id, request);
    }
}
