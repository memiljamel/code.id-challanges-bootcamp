package com.codeid.oe.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Short categoryId;

    @NotEmpty
    @Size(min = 3, max = 15)
    private String categoryName;

    @Size(min = 3)
    private String description;

    private byte[] picture;

    private Instant createdDate;

    private Instant modifiedDate;
}
