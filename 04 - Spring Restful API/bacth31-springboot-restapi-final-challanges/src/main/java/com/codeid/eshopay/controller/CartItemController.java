package com.codeid.eshopay.controller;

import com.codeid.eshopay.model.request.CreateCartItemRequest;
import com.codeid.eshopay.model.request.UpdateCartItemRequest;
import com.codeid.eshopay.model.response.ApiResponse;
import com.codeid.eshopay.model.response.CartItemResponse;
import com.codeid.eshopay.service.CartItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/carts/{cartId}/items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @GetMapping(
            path = "/selected",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<List<CartItemResponse>>> getById(
            @PathVariable Integer cartId
    ) {
        List<CartItemResponse> data = cartItemService.findSelectedByCartId(cartId);

        ApiResponse<List<CartItemResponse>> response = ApiResponse.<List<CartItemResponse>>builder()
                .status(HttpStatus.OK)
                .code(200)
                .message("Data retrieved successfully")
                .data(data)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(
            path = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<CartItemResponse>> create(
            @PathVariable Integer cartId,
            @RequestBody @Valid CreateCartItemRequest request
    ) {
        request.setCartId(cartId);

        CartItemResponse data = cartItemService.save(request);

        ApiResponse<CartItemResponse> response = ApiResponse.<CartItemResponse>builder()
                .status(HttpStatus.CREATED)
                .code(200)
                .message("Data created successfully")
                .data(data)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(
            path = "/{productId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<CartItemResponse>> update(
            @PathVariable Integer cartId,
            @PathVariable Short productId,
            @Valid @RequestBody UpdateCartItemRequest request
    ) {
        request.setCartId(cartId);
        request.setProductId(productId);

        CartItemResponse data = cartItemService.update(request);

        ApiResponse<CartItemResponse> response = ApiResponse.<CartItemResponse>builder()
                .status(HttpStatus.CREATED)
                .code(201)
                .message("Data updated successfully")
                .data(data)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(
            path = "/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> delete(
            @PathVariable Integer cartId,
            @PathVariable Short productId
    ) {
        cartItemService.delete(cartId, productId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
