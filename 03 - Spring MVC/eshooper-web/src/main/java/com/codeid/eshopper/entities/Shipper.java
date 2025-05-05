package com.codeid.eshopper.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "shippers", schema = "oe")
public class Shipper {

    @Id
    @Column(name = "shipper_id")
    @NotNull
    @PositiveOrZero
    private Long shipperId;

    @Column(name = "company_name")
    @NotBlank
    @Size(min = 3, max = 40)
    private String companyName;

    @Column(name = "phone")
    @NotBlank
    @Size(min = 8, max = 24)
    private String phone;

    public Shipper() {
    }

    public Shipper(
            @NotNull @PositiveOrZero Long shipperId,
            @NotBlank @Size(min = 3, max = 40) String companyName,
            @NotBlank @Size(min = 3, max = 24) String phone
    ) {
        this.shipperId = shipperId;
        this.companyName = companyName;
        this.phone = phone;
    }

    public Long getShipperId() {
        return shipperId;
    }

    public void setShipperId(Long shipperId) {
        this.shipperId = shipperId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
