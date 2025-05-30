package com.codeid.eshopay.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCartRequest {

    @NotNull
    @PositiveOrZero
    private Integer userId;

    @Valid
    private List<CreateCartItemRequest> items;
}
