package com.codeid.eshopper.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "regions", schema = "hr")
public class Region {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "region_seq"
    )
    @SequenceGenerator(
            name = "region_seq",
            sequenceName = "hr.regions_region_id_seq",
            allocationSize = 1
    )
    @Column(name = "region_id")
    private Long regionId;

    @Column(name = "region_name")
    @Size(max = 25, message = "region_name maksimal 25 karakter")
    @NotBlank(message = "region_name harus diisi")
    private String regionName;

    public Region() {
    }

    public Region(
            Long regionId,
            @Size(max = 25, message = "region_name maksimal 25 karakter") @NotBlank(message = "region_name harus diisi") String regionName
    ) {
        this.regionId = regionId;
        this.regionName = regionName;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
