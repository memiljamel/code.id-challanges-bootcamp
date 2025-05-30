package com.codeid.eshopay.controller;

import com.codeid.eshopay.model.request.CreateOrderRequest;
import com.codeid.eshopay.model.response.ApiResponse;
import com.codeid.eshopay.model.response.OrderResponse;
import com.codeid.eshopay.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<OrderResponse>> getById(@PathVariable Short id) {
        OrderResponse data = orderService.findById(id);

        ApiResponse<OrderResponse> response = ApiResponse.<OrderResponse>builder()
                .status(HttpStatus.OK)
                .code(200)
                .message("Data retrieved successfully by ID")
                .data(data)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(
            path = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<OrderResponse>> create(
            @RequestBody @Valid CreateOrderRequest request
    ) {
        OrderResponse data = orderService.save(request);

        ApiResponse<OrderResponse> response = ApiResponse.<OrderResponse>builder()
                .status(HttpStatus.CREATED)
                .code(201)
                .message("Data created successfully")
                .data(data)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> delete(@PathVariable Short id) {
        orderService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
