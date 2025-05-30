package com.codeid.eshopay.service;

import com.codeid.eshopay.model.request.CreateCartItemRequest;
import com.codeid.eshopay.model.request.UpdateCartItemRequest;
import com.codeid.eshopay.model.response.CartItemResponse;

import java.util.List;

public interface CartItemService {

    List<CartItemResponse> findSelectedByCartId(Integer cartId);

    CartItemResponse save(CreateCartItemRequest request);

    CartItemResponse update(UpdateCartItemRequest request);

    void delete(Integer cartId, Short productId);
}
