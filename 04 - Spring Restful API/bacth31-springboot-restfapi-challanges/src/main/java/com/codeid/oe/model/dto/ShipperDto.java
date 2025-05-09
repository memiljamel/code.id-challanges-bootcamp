package com.codeid.oe.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipperDto {

    private Short shipperId;

    @NotBlank
    @Size(min = 3, max = 40)
    private String companyName;

    @NotBlank
    @Size(min = 8, max = 24)
    private String phone;
}
