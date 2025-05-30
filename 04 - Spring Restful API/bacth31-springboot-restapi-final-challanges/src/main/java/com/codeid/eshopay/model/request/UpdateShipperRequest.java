package com.codeid.eshopay.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateShipperRequest extends UpdateIdRequest<Short> {

    @NotBlank
    @Size(min = 3, max = 40)
    private String companyName;

    @NotBlank
    @Size(min = 8, max = 24)
    @Pattern(regexp = "^[+0-9\\s\\-()]+$")
    private String phone;
}
