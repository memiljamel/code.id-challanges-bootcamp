package com.codeid.oe.controller;

import com.codeid.oe.model.dto.ProductDto;
import com.codeid.oe.model.dto.ProductImageDto;
import com.codeid.oe.model.enumeration.StatusEnum;
import com.codeid.oe.model.response.ApiResponse;
import com.codeid.oe.service.BaseCrudService;
import com.codeid.oe.service.ProductService;
import com.codeid.oe.service.implementation.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController extends BaseMultipartController<ProductDto, Short> {

    private final ProductService productService;
    private final FileStorageService fileStorageService;

    @Override
    protected BaseCrudService<ProductDto, Short> getService() {
        return productService;
    }

    @Override
    public ResponseEntity<?> createMultipart(ProductDto dto, MultipartFile file, String description) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload product picture");
        }

        try {
            String filename = fileStorageService.storeFileWithRandomName(file);
            dto.setPicture(filename);

            ProductDto productDto = productService.save(dto);

            ApiResponse<ProductDto> response = new ApiResponse<>(
                    StatusEnum.Success,
                    "Data created successfully",
                    productDto
            );

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError()
                    .body(Collections.singletonMap("error", ex.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> viewImage(String fileName) {
        try {
            Resource resource = fileStorageService.loadFile(fileName);

            String contentType = determineContentType(fileName);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> updateMultipart(Short id, ProductDto dto, MultipartFile file, String description) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload product picture");
        }

        try {
            String filename = fileStorageService.storeFileWithRandomName(file);
            dto.setPicture(filename);

            ProductDto productDto = productService.update(id, dto);

            ApiResponse<ProductDto> response = new ApiResponse<>(
                    StatusEnum.Success,
                    "Data updated successfully",
                    productDto
            );

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError()
                    .body(Collections.singletonMap("error", ex.getMessage()));
        }
    }

    @GetMapping("/{id}/uploadMultipleImages")
    public ResponseEntity<ApiResponse<List<ProductImageDto>>> getAllMultipartBulk(
            @PathVariable Short id
    ) {
        ApiResponse<List<ProductImageDto>> response = new ApiResponse<>(
                StatusEnum.Success,
                "Data retrieved successfully",
                productService.bulkFindAll(id)
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping(
            value = "/{id}/uploadMultipleImages",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<?> createMultipartBulk(
            @PathVariable Short id,
            @RequestPart(value = "files", required = false) MultipartFile[] files,
            @RequestParam(value = "description", required = false) String description
    ) {
        if (files.length == 0) {
            return ResponseEntity.badRequest().body("Please upload product images");
        }

        try {
            List<String> filenames = new ArrayList<>();

            for (var file : files) {
                String filename = fileStorageService.storeFileWithRandomName(file);

                filenames.add(filename);
            }

            List<ProductImageDto> productImagesDto = productService.bulkCreate(id, files, filenames);

            ApiResponse<List<ProductImageDto>> response = new ApiResponse<>(
                    StatusEnum.Success,
                    "Data created successfully",
                    productImagesDto
            );

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError()
                    .body(Collections.singletonMap("error", ex.getMessage()));
        }
    }
}
