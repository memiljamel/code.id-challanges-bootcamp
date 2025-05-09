package com.codeid.oe.model.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "shippers", schema = "oe")
public class Shipper extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipper_id")
    private Short shipperId;

    @Nonnull
    @Column(name = "company_name", length = 40, nullable = false)
    private String companyName;

    @Column(length = 24)
    private String phone;
}
