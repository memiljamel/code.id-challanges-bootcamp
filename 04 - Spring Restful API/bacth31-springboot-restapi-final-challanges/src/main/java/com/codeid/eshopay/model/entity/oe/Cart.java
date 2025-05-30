package com.codeid.eshopay.model.entity.oe;

import com.codeid.eshopay.model.entity.AuditableEntity;
import com.codeid.eshopay.model.entity.person.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carts", schema = "oe")
public class Cart extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq")
    @SequenceGenerator(name = "cart_seq", sequenceName = "oe.carts_cart_id_seq", allocationSize = 1)
    @Column(name = "cart_id")
    private Integer cartId;

    @CreatedDate
    @Column(name = "created_on")
    private Instant createdOn;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart")
    private Set<CartItem> cartItems;
}
