package com.codeid.oe.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDto {

    private Short supplierId;

    @NotNull
    @Size(min = 3, max = 40)
    private String companyName;

    @Size(min = 3, max = 30)
    private String contactName;

    @Size(min = 3, max = 30)
    private String contactTitle;

    @Size(min = 8, max = 24)
    private String phone;

    @Size(min = 8, max = 24)
    private String fax;

    private String homepage;

    private Instant createdDate;

    private Instant modifiedDate;
}
