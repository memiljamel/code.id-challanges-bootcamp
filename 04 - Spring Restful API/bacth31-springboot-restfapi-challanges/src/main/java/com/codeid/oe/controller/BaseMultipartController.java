package com.codeid.oe.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

public abstract class BaseMultipartController<T, ID> extends BaseCrudController<T, ID> {

    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public abstract ResponseEntity<?> createMultipart(
            @RequestPart("data") T dto,
            @RequestPart(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "description", required = false) String description
    );

    @GetMapping("/view/{fileName:.+}")
    public abstract ResponseEntity<?> viewImage(@PathVariable String fileName);

    public String determineContentType(String fileName) {
        if (fileName.toLowerCase().endsWith(".png")) {
            return "image/png";
        } else if (fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (fileName.toLowerCase().endsWith(".gif")) {
            return "image/gif";
        }
        return "application/octet-stream";
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public abstract ResponseEntity<?> updateMultipart(
            @PathVariable ID id, @RequestPart("data") T dto,
            @RequestPart(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "description", required = false) String description
    );
}
