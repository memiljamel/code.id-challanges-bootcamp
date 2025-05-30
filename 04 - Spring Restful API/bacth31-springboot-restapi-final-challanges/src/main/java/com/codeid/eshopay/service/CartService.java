package com.codeid.eshopay.service;

import com.codeid.eshopay.model.request.CreateCartRequest;
import com.codeid.eshopay.model.request.CreateCheckoutRequest;
import com.codeid.eshopay.model.request.UpdateCartRequest;
import com.codeid.eshopay.model.response.CartResponse;

public interface CartService extends BaseCrudService<
        CreateCartRequest,
        UpdateCartRequest,
        CartResponse,
        Integer> {

    CartResponse findByUserId(Integer userId);

    CartResponse checkout(CreateCheckoutRequest request);
}
