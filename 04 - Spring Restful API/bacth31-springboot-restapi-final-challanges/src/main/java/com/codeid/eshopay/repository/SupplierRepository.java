package com.codeid.eshopay.repository;

import com.codeid.eshopay.model.entity.oe.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Short> {
}
