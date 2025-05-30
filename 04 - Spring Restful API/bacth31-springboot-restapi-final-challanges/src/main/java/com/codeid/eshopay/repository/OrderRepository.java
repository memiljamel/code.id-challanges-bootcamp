package com.codeid.eshopay.repository;

import com.codeid.eshopay.model.entity.oe.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Short> {
}
