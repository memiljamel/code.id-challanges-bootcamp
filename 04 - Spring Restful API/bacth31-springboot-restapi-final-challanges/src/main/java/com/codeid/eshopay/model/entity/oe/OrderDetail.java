package com.codeid.eshopay.model.entity.oe;

import com.codeid.eshopay.model.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_details", schema = "oe")
public class OrderDetail extends AuditableEntity {

    @EmbeddedId
    private OrderDetailId id;

    @Column(name = "unit_price", precision = 8, scale = 2)
    private BigDecimal unitPrice;

    private Integer quantity;

    @Column(precision = 5, scale = 2)
    private BigDecimal discount;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;
}
