package com.codeid.eshopay.service;

import com.codeid.eshopay.model.request.CreateOrderRequest;
import com.codeid.eshopay.model.response.OrderResponse;

public interface OrderService {

    OrderResponse findById(Short id);

    OrderResponse save(CreateOrderRequest request);

    void delete(Short id);
}
