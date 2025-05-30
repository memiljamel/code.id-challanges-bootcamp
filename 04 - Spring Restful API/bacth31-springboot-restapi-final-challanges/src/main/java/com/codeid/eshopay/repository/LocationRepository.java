package com.codeid.eshopay.repository;

import com.codeid.eshopay.model.entity.hr.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
}
