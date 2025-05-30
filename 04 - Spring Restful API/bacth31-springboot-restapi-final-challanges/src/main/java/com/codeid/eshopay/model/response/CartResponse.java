package com.codeid.eshopay.model.response;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {

    private Integer cartId;

    private Integer userId;

    private List<CartItemResponse> items;

    private Instant createdDate;

    private Instant modifiedDate;
}
