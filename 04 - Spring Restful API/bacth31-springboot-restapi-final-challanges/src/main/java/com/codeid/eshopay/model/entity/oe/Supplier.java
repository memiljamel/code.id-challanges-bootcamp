package com.codeid.eshopay.model.entity.oe;

import com.codeid.eshopay.model.entity.AuditableEntity;
import com.codeid.eshopay.model.entity.hr.Location;
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
@Table(name = "suppliers", schema = "oe")
public class Supplier extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supplier_seq")
    @SequenceGenerator(name = "supplier_seq", sequenceName = "oe.suppliers_supplier_id_seq", allocationSize = 1)
    @Column(name = "supplier_id")
    private Short supplierId;

    @Column(name = "company_name", length = 40, nullable = false)
    private String companyName;

    @Column(name = "contact_name", length = 30)
    private String contactName;

    @Column(name = "contact_title", length = 30)
    private String contactTitle;

    @Column(length = 24)
    private String phone;

    @Column(length = 24)
    private String fax;

    @Column(columnDefinition = "text")
    private String homepage;

    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    private Location location;

    @OneToMany(mappedBy = "supplier")
    private List<Product> products;
}
