package com.codeid.eshopay.repository;

import com.codeid.eshopay.model.entity.oe.Cart;
import com.codeid.eshopay.model.entity.person.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    Optional<Cart> findByUser(User user);
}
