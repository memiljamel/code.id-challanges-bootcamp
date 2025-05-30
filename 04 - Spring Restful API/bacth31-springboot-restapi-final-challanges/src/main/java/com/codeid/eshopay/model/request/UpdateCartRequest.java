package com.codeid.eshopay.model.request;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCartRequest extends UpdateIdRequest<Integer> {

    private Boolean merge;

    @Valid
    private List<UpdateCartItemRequest> items;
}
