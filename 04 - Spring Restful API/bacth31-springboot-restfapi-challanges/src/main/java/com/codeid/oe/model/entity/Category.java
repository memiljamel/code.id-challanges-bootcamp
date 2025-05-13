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
@Table(name = "categories", schema = "oe")
public class Category extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Short categoryId;

    @Nonnull
    @Column(name = "category_name", length = 15, nullable = false)
    private String categoryName;

    @Column(columnDefinition = "text")
    private String description;

    private byte[] picture;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
