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
public class CartItemId implements Serializable {

    @Column(name = "cart_id")
    private Integer cartId;

    @Column(name = "product_id")
    private Short productId;
}
