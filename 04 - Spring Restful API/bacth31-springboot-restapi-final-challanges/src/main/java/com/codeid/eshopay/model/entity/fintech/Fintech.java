package com.codeid.eshopay.model.entity.fintech;

import com.codeid.eshopay.model.entity.AuditableEntity;
import com.codeid.eshopay.model.enumeration.FintechType;
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
@Table(name = "fintechs", schema = "fintech")
public class Fintech extends AuditableEntity {

    @Id
    @Column(name = "fint_code", length = 4)
    private String fintCode;

    @Column(name = "fint_name", length = 125)
    private String fintName;

    @Column(name = "fint_short_name", length = 15)
    private String fintShortName;

    @Enumerated(EnumType.STRING)
    @Column(name = "fint_type", length = 10)
    private FintechType fintType;

    @OneToMany(mappedBy = "fintech")
    private List<Account> accounts;
}
