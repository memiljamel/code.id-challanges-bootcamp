package com.codeid.eshopay.model.entity.hr;

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
@Table(name = "regions", schema = "hr")
public class Region extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "region_seq")
    @SequenceGenerator(name = "region_seq", sequenceName = "hr.regions_region_id_seq", allocationSize = 1)
    @Column(name = "region_id")
    private Integer regionId;

    @Column(name = "region_name", length = 25)
    private String regionName;

    @OneToMany(mappedBy = "region")
    private List<Country> countries;
}
