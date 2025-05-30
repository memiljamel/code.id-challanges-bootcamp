package com.codeid.eshopay.model.response;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {

    private Short categoryId;

    private String categoryName;

    private String description;

    private byte[] picture;

    private Instant createdDate;

    private Instant modifiedDate;
}
