package com.codeid.usersmanagement.model.entity;

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
@Table(name = "users", schema = "person")
public class User extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "person.users_user_id_seq", allocationSize = 1)
    @Column(name = "user_id")
    private Short userId;

    @Column(length = 100, nullable = false, unique = true)
    private String username;

    @Column(length = 125, nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Role> roles;
}
