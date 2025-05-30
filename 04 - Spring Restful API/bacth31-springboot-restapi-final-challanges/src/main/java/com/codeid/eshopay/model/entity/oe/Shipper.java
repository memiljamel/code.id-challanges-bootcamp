package com.codeid.eshopay.model.entity.oe;

import com.codeid.eshopay.model.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shippers", schema = "oe")
public class Shipper extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipper_seq")
    @SequenceGenerator(name = "shipper_seq", sequenceName = "oe.shippers_shipper_id_seq", allocationSize = 1)
    @Column(name = "shipper_id")
    private Short shipperId;

    @Column(name = "company_name", length = 40, nullable = false)
    private String companyName;

    @Column(length = 24)
    private String phone;

    @OneToMany(mappedBy = "shipper")
    private List<Order> orders;
}
