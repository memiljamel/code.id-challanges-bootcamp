package com.codeid.oe.model.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "products", schema = "oe")
public class Product extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Short productId;

    @Nonnull
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

    @Column(name = "unit_price")
    private Float unitPrice;

    @Column(name = "units_in_stock")
    private Short unitsInStock;

    @Column(name = "units_in_order")
    private Short unitsInOrder;

    @Column(name = "reorder_level")
    private Short reorderLevel;

    @Nonnull
    @Column(nullable = false)
    private Integer discontinued;

    @Column(columnDefinition = "text")
    private String picture;

    @OneToMany(mappedBy = "product")
    private List<ProductImage> productImages;
}
