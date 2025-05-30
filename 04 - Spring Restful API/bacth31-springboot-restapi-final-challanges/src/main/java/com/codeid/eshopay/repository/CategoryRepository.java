package com.codeid.eshopay.repository;

import com.codeid.eshopay.model.entity.oe.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Short> {
}
