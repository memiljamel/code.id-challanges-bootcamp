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
@Table(name = "countries", schema = "hr")
public class Country extends AuditableEntity {

    @Id
    @Column(name = "country_id", length = 2)
    private String countryId;

    @Column(name = "country_name", length = 200)
    private String countryName;

    @ManyToOne
    @JoinColumn(name = "region_id", referencedColumnName = "region_id")
    private Region region;

    @OneToMany(mappedBy = "country")
    private List<Location> locations;
}
