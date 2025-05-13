package com.codeid.oe.model.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @NotNull
    @PositiveOrZero
    private Short productId;

    @NotNull
    @Size(min = 3, max = 40)
    private String productName;

    @NotNull
    @PositiveOrZero
    private Short supplierId;

    @NotNull
    @PositiveOrZero
    private Short categoryId;

    @Size(max = 20)
    private String quantityPerUnit;

    @PositiveOrZero
    private Float unitPrice;

    @PositiveOrZero
    private Short unitsInStock;

    @PositiveOrZero
    private Short unitsInOrder;

    @PositiveOrZero
    private Short reorderLevel;

    @NotNull
    @Min(value = 0)
    @Max(value = 1)
    private Integer discontinued;

    private String picture;

    private Instant createdDate;

    private Instant modifiedDate;
}
