package com.codeid.eshopay.model.entity.oe;

import com.codeid.eshopay.model.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart_items", schema = "oe")
public class CartItem extends AuditableEntity {

    @EmbeddedId
    private CartItemId id;

    @Column(name = "unit_price", precision = 8, scale = 2)
    private BigDecimal unitPrice;

    private Integer quantity;

    @Column(precision = 5, scale = 2)
    private BigDecimal discount;

    @CreatedDate
    @Column(name = "created_on")
    private Instant createdOn;

    @ColumnDefault(value = "false")
    private Boolean selected;

    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    private Cart cart;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @PrePersist
    private void setSelected() {
        this.selected = false;
    }
}
