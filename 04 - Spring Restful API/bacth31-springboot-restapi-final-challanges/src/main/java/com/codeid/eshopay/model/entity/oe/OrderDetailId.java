package com.codeid.eshopay.model.entity.oe;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class OrderDetailId implements Serializable {

    @Column(name = "order_id")
    private Short orderId;

    @Column(name = "product_id")
    private Short productId;
}
