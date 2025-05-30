package com.codeid.eshopay.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BulkProductImageRequest {

    @JsonIgnore
    private Short productId;

    private Boolean merge;

    @NotNull
    @Size(min = 1, max = 10)
    private List<MultipartFile> images;
}
