package com.codeid.oe.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageDto {

    private Short imageId;

    private String fileName;

    private Long fileSize;

    private String fileType;

    private String fileUri;

    private Short productId;
}
