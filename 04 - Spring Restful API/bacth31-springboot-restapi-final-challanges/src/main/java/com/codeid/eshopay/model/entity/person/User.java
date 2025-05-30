package com.codeid.eshopay.model.entity.person;

import com.codeid.eshopay.model.entity.AuditableEntity;
import com.codeid.eshopay.model.entity.fintech.Account;
import com.codeid.eshopay.model.entity.oe.Cart;
import com.codeid.eshopay.model.entity.oe.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", schema = "person")
public class User extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "person.users_user_id_seq", allocationSize = 1)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name", length = 15, nullable = false)
    private String userName;

    @Column(name = "user_email", length = 80, nullable = false, unique = true)
    private String userEmail;

    @Column(name = "user_password", length = 125, nullable = false)
    private String userPassword;

    @Column(name = "user_handphone", length = 15, nullable = false, unique = true)
    private String userHandphone;

    @CreatedDate
    @Column(name = "created_on")
    private Instant createdOn;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    @OneToMany(mappedBy = "user")
    private List<Account> accounts;
}
