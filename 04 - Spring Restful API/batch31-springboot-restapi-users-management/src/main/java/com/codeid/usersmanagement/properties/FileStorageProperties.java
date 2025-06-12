package com.codeid.usersmanagement.properties;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {

    @NotBlank
    private String uploadDir;
}