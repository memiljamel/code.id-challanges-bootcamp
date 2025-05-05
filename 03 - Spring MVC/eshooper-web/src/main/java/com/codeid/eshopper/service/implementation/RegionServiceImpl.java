package com.codeid.eshopper.service.implementation;

import com.codeid.eshopper.entities.Region;
import com.codeid.eshopper.repository.RegionRepository;
import com.codeid.eshopper.service.RegionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RegionServiceImpl implements RegionService {

    private final RegionRepository repository;

    public RegionServiceImpl(RegionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Region> findAllCategory() {
        return repository.findAll();
    }

    @Override
    public Region addRegion(Region region) {
        return repository.save(region);
    }

    @Override
    public Optional<Region> findRegionById(Long regionId) {
        return repository.findById(regionId);
    }

    @Override
    public void deleteRegionById(Long regionId) {
        repository.deleteById(regionId);
    }
}
