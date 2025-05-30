package com.codeid.eshopay.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    @NotNull
    @PositiveOrZero
    private Integer userId;

    @NotNull
    @PositiveOrZero
    private Integer locationId;

    @NotNull
    @PositiveOrZero
    private Short shipperId;

    @NotBlank
    @Size(min = 1, max = 4)
    private String bankCode;
}
