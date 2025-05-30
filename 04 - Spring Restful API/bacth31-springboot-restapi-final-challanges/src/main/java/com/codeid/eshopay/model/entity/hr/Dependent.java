package com.codeid.eshopay.model.entity.hr;

import com.codeid.eshopay.model.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dependents", schema = "hr")
public class Dependent extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dependent_seq")
    @SequenceGenerator(name = "dependent_seq", sequenceName = "hr.dependents_dependent_id_seq", allocationSize = 1)
    @Column(name = "dependent_id")
    private Integer dependentId;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(length = 25, nullable = false)
    private String relationship;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employee employee;
}
