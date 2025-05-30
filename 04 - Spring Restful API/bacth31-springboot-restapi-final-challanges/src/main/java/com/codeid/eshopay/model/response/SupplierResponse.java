package com.codeid.eshopay.model.response;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierResponse {

    private Short supplierId;

    private String companyName;

    private String contactName;

    private String contactTitle;

    private String phone;

    private String fax;

    private String homepage;

    private Instant createdDate;

    private Instant modifiedDate;
}
