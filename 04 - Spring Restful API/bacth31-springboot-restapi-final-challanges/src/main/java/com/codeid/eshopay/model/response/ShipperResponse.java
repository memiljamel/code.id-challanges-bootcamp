package com.codeid.eshopay.model.response;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShipperResponse {

    private Short shipperId;

    private String companyName;

    private String phone;

    private Instant createdDate;

    private Instant modifiedDate;
}
