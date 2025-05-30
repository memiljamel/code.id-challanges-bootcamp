package com.codeid.eshopay.model.entity.oe;

import com.codeid.eshopay.model.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products", schema = "oe")
public class Product extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "oe.products_product_id_seq", allocationSize = 1)
    @Column(name = "product_id")
    private Short productId;

    @Column(name = "product_name", length = 40, nullable = false)
    private String productName;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @Column(name = "quantity_per_unit", length = 20)
    private String quantityPerUnit;

    @Column(name = "unit_price", precision = 8, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "units_in_stock")
    private Short unitsInStock;

    @Column(name = "units_in_order")
    private Short unitsInOrder;

    @Column(name = "reorder_level")
    private Short reorderLevel;

    @Column(nullable = false)
    private Integer discontinued;

    @Column(columnDefinition = "text")
    private String picture;

    @OneToMany(mappedBy = "product")
    private List<ProductImage> productImages = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private Set<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "product")
    private Set<CartItem> cartItems;
}
