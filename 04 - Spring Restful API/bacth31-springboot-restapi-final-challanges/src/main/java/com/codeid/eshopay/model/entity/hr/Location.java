package com.codeid.eshopay.model.entity.hr;

import com.codeid.eshopay.model.entity.AuditableEntity;
import com.codeid.eshopay.model.entity.oe.Order;
import com.codeid.eshopay.model.entity.oe.Supplier;
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
@Table(name = "locations", schema = "hr")
public class Location extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_seq")
    @SequenceGenerator(name = "location_seq", sequenceName = "hr.locations_location_id_seq", allocationSize = 1)
    @Column(name = "location_id")
    private Integer locationId;

    @Column(name = "street_address", length = 60)
    private String streetAddress;

    @Column(name = "postal_code", length = 12)
    private String postalCode;

    @Column(length = 30, nullable = false)
    private String city;

    @Column(name = "state_province", length = 25)
    private String stateProvince;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
    private Country country;

    @OneToMany(mappedBy = "location")
    private List<Department> departments;

    @OneToOne(mappedBy = "location")
    private Supplier supplier;

    @OneToMany(mappedBy = "location")
    private List<Order> orders;
}
