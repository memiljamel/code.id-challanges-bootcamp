package com.codeid.eshopay.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCartItemRequest {

    @JsonIgnore
    private Integer cartId;

    @NotNull
    @PositiveOrZero
    private Short productId;

    @NotNull
    @PositiveOrZero
    private Integer quantity;

    @PositiveOrZero
    @Digits(integer = 3, fraction = 2)
    @DecimalMax(value = "100.00")
    private BigDecimal discount;
}
