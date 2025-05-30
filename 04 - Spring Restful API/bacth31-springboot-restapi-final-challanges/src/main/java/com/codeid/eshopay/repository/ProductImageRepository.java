package com.codeid.eshopay.repository;

import com.codeid.eshopay.model.entity.oe.Product;
import com.codeid.eshopay.model.entity.oe.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Short> {

    Optional<ProductImage> findByProductAndImageId(Product product, Short imageId);

    void deleteByProduct(Product product);
}
