package com.codeid.eshopay.controller;

import com.codeid.eshopay.model.request.CreateCartRequest;
import com.codeid.eshopay.model.request.CreateCheckoutRequest;
import com.codeid.eshopay.model.request.UpdateCartRequest;
import com.codeid.eshopay.model.response.ApiResponse;
import com.codeid.eshopay.model.response.CartResponse;
import com.codeid.eshopay.service.BaseCrudService;
import com.codeid.eshopay.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/carts")
public class CartController extends BaseCrudController<
        CreateCartRequest,
        UpdateCartRequest,
        CartResponse,
        Integer> {

    @Autowired
    private CartService cartService;

    @Override
    protected BaseCrudService<CreateCartRequest, UpdateCartRequest, CartResponse, Integer> getService() {
        return cartService;
    }

    @GetMapping(
            path = "/user/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<CartResponse>> getByUserId(@PathVariable Integer userId) {
        CartResponse data = cartService.findByUserId(userId);

        ApiResponse<CartResponse> response = ApiResponse.<CartResponse>builder()
                .status(HttpStatus.OK)
                .code(200)
                .message("Data retrieved successfully by ID")
                .data(data)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(
            path = "/{cartId}/checkout",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<CartResponse>> create(
            @PathVariable Integer cartId,
            @RequestBody @Valid CreateCheckoutRequest request
    ) {
        request.setCartId(cartId);

        CartResponse data = cartService.checkout(request);

        ApiResponse<CartResponse> response = ApiResponse.<CartResponse>builder()
                .status(HttpStatus.CREATED)
                .code(201)
                .message("Data created successfully")
                .data(data)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
