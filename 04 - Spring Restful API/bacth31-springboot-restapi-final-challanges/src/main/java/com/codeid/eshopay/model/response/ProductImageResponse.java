package com.codeid.eshopay.model.response;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageResponse {

    private Short imageId;

    private String fileName;

    private Long fileSize;

    private String fileType;

    private String fileUri;

    private Instant createdDate;

    private Instant modifiedDate;
}
