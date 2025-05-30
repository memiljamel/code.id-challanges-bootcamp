package com.codeid.eshopay.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {

    @NotBlank
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
    @Digits(integer = 8, fraction = 2)
    private BigDecimal unitPrice;

    @PositiveOrZero
    private Short unitsInStock;

    @PositiveOrZero
    private Short unitsInOrder;

    @PositiveOrZero
    private Short reorderLevel;

    @NotNull
    @Range(min = 0, max = 1)
    private Integer discontinued;

    @JsonIgnore
    private MultipartFile picture;
}
