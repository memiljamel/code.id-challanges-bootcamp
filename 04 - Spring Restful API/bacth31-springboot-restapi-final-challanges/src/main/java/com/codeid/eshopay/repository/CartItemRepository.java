package com.codeid.eshopay.repository;

import com.codeid.eshopay.model.entity.oe.Cart;
import com.codeid.eshopay.model.entity.oe.CartItem;
import com.codeid.eshopay.model.entity.oe.CartItemId;
import com.codeid.eshopay.model.entity.oe.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, CartItemId> {

    List<CartItem> findByCartAndSelectedTrue(Cart cart);

    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

    @Modifying
    @Query("""
            UPDATE CartItem ci
            SET ci.selected = false
            WHERE ci.cart = :cart
            """)
    void unselectAllByCart(@Param(value = "cart") Cart cart);

    void deleteAllByCart(Cart cart);
}
