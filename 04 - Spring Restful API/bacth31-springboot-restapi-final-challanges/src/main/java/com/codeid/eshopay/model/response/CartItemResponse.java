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
public class CartItemResponse {

    private Integer cartId;

    private Short productId;

    private String productName;

    private String productPictureUrl;

    private List<String> productImageUrls;

    private String supplierName;

    private BigDecimal unitPrice;

    private Integer quantity;

    private BigDecimal discount;

    private String discountPercentage;

    private Boolean selected;

    private Instant createdDate;

    private Instant modifiedDate;
}
