package com.codeid.eshopper.service;

import com.codeid.eshopper.entities.Region;

import java.util.List;
import java.util.Optional;

public interface RegionService {

    List<Region> findAllCategory();

    Region addRegion(Region region);

    Optional<Region> findRegionById(Long regionId);

    void deleteRegionById(Long regionId);
}
