package com.codeid.eshopay.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCheckoutRequest {

    @JsonIgnore
    private Integer cartId;

    @NotNull
    @Size(min = 1)
    List<@NotNull @PositiveOrZero Short> productIds;
}
