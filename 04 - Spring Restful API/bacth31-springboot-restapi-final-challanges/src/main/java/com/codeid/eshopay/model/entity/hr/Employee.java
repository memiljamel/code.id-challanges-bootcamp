package com.codeid.eshopay.model.entity.hr;

import com.codeid.eshopay.model.entity.AuditableEntity;
import com.codeid.eshopay.model.entity.oe.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees", schema = "hr")
public class Employee extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @SequenceGenerator(name = "employee_seq", sequenceName = "hr.employees_employee_id_seq", allocationSize = 1)
    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 20)
    private String lastName;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "job_id")
    private Job job;

    @Column(precision = 8, scale = 2, nullable = false)
    private BigDecimal salary;

    @OneToMany(mappedBy = "employeeId")
    private List<Employee> managerIdBy;

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "employee_id")
    private Employee managerId;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    private Department department;

    @OneToMany(mappedBy = "employee")
    private List<Dependent> dependents;

    @OneToMany(mappedBy = "employee")
    private List<Order> orders;
}
