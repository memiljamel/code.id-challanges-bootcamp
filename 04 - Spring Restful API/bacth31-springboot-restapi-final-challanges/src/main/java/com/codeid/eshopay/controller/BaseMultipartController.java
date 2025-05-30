package com.codeid.eshopay.controller;

import com.codeid.eshopay.model.request.UpdateIdRequest;
import com.codeid.eshopay.model.response.ApiResponse;
import com.codeid.eshopay.service.FileStorageService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;

public abstract class BaseMultipartController<CreateReq, UpdateReq extends UpdateIdRequest<ID>, Res, ID>
        extends BaseCrudController<CreateReq, UpdateReq, Res, ID> {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping(
            path = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public abstract ResponseEntity<ApiResponse<Res>> upload(
            @RequestPart(name = "data") @Valid CreateReq request,
            @RequestPart(name = "file") MultipartFile file
    );

    @PutMapping(
            path = "/{id}/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public abstract ResponseEntity<ApiResponse<Res>> upload(
            @PathVariable ID id,
            @RequestPart(name = "data") @Valid UpdateReq request,
            @RequestPart(name = "file") MultipartFile file
    );

    @SneakyThrows
    @GetMapping(
            path = "/files/{filename:.+}",
            produces = MediaType.ALL_VALUE
    )
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = fileStorageService.load(filename);

        if (file == null || !file.exists()) {
            throw new EntityNotFoundException("File not found with filename" + filename);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"");
        headers.add(HttpHeaders.CONTENT_TYPE, Files.probeContentType(file.getFile().toPath()));

        return new ResponseEntity<>(file, headers, HttpStatus.OK);
    }
}
