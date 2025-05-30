package com.codeid.eshopay.model.entity.fintech;

import com.codeid.eshopay.model.entity.AuditableEntity;
import com.codeid.eshopay.model.entity.oe.Order;
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
@Table(name = "bank", schema = "fintech")
public class Bank extends AuditableEntity {

    @Id
    @Column(name = "bank_code", length = 4)
    private String bankCode;

    @Column(name = "bank_name", length = 55)
    private String bankName;

    @OneToMany(mappedBy = "bank")
    private List<Order> orders;
}
