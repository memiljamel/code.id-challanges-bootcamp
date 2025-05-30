package com.codeid.eshopay.model.entity.hr;

import com.codeid.eshopay.model.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jobs", schema = "hr")
public class Job extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_seq")
    @SequenceGenerator(name = "job_seq", sequenceName = "hr.jobs_job_id_seq", allocationSize = 1)
    @Column(name = "job_id")
    private Integer jobId;

    @Column(name = "job_title", length = 35, nullable = false)
    private String jobTitle;

    @Column(name = "min_salary", precision = 8, scale = 2)
    private BigDecimal minSalary;

    @Column(name = "max_salary", precision = 8, scale = 2)
    private BigDecimal maxSalary;

    @OneToMany(mappedBy = "job")
    private List<Employee> employees;
}
