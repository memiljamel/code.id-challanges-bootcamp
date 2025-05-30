package com.codeid.eshopay.repository;

import com.codeid.eshopay.model.entity.oe.OrderDetail;
import com.codeid.eshopay.model.entity.oe.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
}
