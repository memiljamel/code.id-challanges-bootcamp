package com.codeid.oe.repository;

import com.codeid.oe.model.entity.Product;
import com.codeid.oe.model.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Short> {

    List<ProductImage> findAllByProduct(Product product);

    Optional<ProductImage> findByProductAndImageId(Product product, Short imageId);

    void deleteByProduct(Product product);
}
