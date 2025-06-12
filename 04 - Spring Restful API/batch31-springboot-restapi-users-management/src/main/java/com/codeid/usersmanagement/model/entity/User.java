package com.codeid.usersmanagement.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", schema = "person")
public class User {

    @Id
    @Column(length = 100)
    private String username;

    @Column(length = 125, nullable = false)
    private String password;

    @OneToOne(mappedBy = "user")
    private Role role;
}
