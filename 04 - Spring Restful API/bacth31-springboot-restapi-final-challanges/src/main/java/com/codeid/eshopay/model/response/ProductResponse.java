package com.codeid.eshopay.model.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Short productId;

    private String productName;

    private SupplierResponse supplier;

    private CategoryResponse category;

    private String quantityPerUnit;

    private BigDecimal unitPrice;

    private Short unitsInStock;

    private Short unitsInOrder;

    private Short reorderLevel;

    private Integer discontinued;

    private String pictureUrl;

    private List<String> productImageUrls;

    private Instant createdDate;

    private Instant modifiedDate;
}
