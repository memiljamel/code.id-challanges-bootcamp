package com.codeid.eshopay.repository;

import com.codeid.eshopay.model.entity.oe.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Short> {
}
