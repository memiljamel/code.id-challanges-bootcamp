package com.codeid.eshopay.repository;

import com.codeid.eshopay.model.entity.oe.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Short> {

    @Query("""
                SELECT p FROM Product p
                    INNER JOIN p.category c
                        WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%'))
                            OR LOWER(c.categoryName) LIKE LOWER(CONCAT('%', :keyword, '%'))
            """)
    List<Product> findByProductNameOrCategoryName(@Param("keyword") String keyword, Pageable pageable);
}
