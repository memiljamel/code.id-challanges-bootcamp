package com.codeid.eshopay.model.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponse {

    private Short orderId;

    private Short productId;

    private BigDecimal unitPrice;

    private Integer quantity;

    private BigDecimal discount;

    private Instant createdDate;

    private Instant modifiedDate;
}
