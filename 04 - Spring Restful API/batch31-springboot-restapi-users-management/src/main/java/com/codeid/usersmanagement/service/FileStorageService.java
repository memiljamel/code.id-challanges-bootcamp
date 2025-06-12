package com.codeid.usersmanagement.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService {

    void init();

    String save(MultipartFile file);

    Resource load(String filename);

    Stream<Path> loadAll();

    void delete(String filename);

    void deleteAll();
}
