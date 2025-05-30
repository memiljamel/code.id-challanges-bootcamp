package com.codeid.eshopay.controller;

import com.codeid.eshopay.model.request.BulkProductImageRequest;
import com.codeid.eshopay.model.request.CreateProductRequest;
import com.codeid.eshopay.model.request.UpdateProductRequest;
import com.codeid.eshopay.model.response.ApiResponse;
import com.codeid.eshopay.model.response.ProductImageResponse;
import com.codeid.eshopay.model.response.ProductResponse;
import com.codeid.eshopay.service.BaseCrudService;
import com.codeid.eshopay.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController extends BaseMultipartController<
        CreateProductRequest,
        UpdateProductRequest,
        ProductResponse,
        Short> {

    @Autowired
    private ProductService productService;

    @Override
    protected BaseCrudService<CreateProductRequest, UpdateProductRequest, ProductResponse, Short> getService() {
        return productService;
    }

    @GetMapping(
            path = "/search",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAll(
            @RequestParam(required = false) String keyword,
            Pageable pageable
    ) {
        List<ProductResponse> data = productService.findAllWithCategory(keyword, pageable);

        ApiResponse<List<ProductResponse>> response = ApiResponse.<List<ProductResponse>>builder()
                .status(HttpStatus.OK)
                .code(200)
                .message("Data retrieved successfully")
                .data(data)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(
            path = "/{productId}/images",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<List<ProductImageResponse>>> bulkInsert(
            @PathVariable Short productId,
            @ModelAttribute @Valid BulkProductImageRequest request
    ) {
        request.setProductId(productId);

        List<ProductImageResponse> data = productService.bulkInsert(request);

        ApiResponse<List<ProductImageResponse>> response = ApiResponse.<List<ProductImageResponse>>builder()
                .status(HttpStatus.CREATED)
                .code(201)
                .message("Data created successfully")
                .data(data)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping(
            path = "/{productId}/images/{imageId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> delete(@PathVariable Short productId, @PathVariable Short imageId) {
        productService.delete(productId, imageId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ApiResponse<ProductResponse>> upload(CreateProductRequest request, MultipartFile file) {
        request.setPicture(file);

        ProductResponse data = productService.save(request);

        ApiResponse<ProductResponse> response = ApiResponse.<ProductResponse>builder()
                .status(HttpStatus.CREATED)
                .code(201)
                .message("Data created successfully")
                .data(data)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ApiResponse<ProductResponse>> upload(Short id, UpdateProductRequest request, MultipartFile file) {
        request.setId(id);
        request.setPicture(file);

        ProductResponse data = productService.update(request);

        ApiResponse<ProductResponse> response = ApiResponse.<ProductResponse>builder()
                .status(HttpStatus.OK)
                .code(200)
                .message("Data updated successfully")
                .data(data)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
