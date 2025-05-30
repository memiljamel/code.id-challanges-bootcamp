package com.codeid.eshopay.model.entity.oe;

import com.codeid.eshopay.model.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories", schema = "oe")
public class Category extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq", sequenceName = "oe.categories_category_id_seq", allocationSize = 1)
    @Column(name = "category_id")
    private Short categoryId;

    @Column(name = "category_name", length = 15, nullable = false)
    private String categoryName;

    @Column(columnDefinition = "text")
    private String description;

    private byte[] picture;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
