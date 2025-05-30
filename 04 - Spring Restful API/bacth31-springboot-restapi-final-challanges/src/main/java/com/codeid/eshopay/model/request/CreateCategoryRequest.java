package com.codeid.eshopay.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryRequest {

    @NotBlank
    @Size(min = 3, max = 15)
    private String categoryName;

    @Size(min = 3, max = 255)
    private String description;

    private MultipartFile picture;
}
