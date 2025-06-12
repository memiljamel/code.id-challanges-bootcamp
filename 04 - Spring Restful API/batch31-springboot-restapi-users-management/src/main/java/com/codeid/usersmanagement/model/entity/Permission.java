package com.codeid.usersmanagement.model.entity;

import com.codeid.usersmanagement.model.enumeration.PermissionType;
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
@Table(name = "permissions", schema = "person")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permission_seq")
    @SequenceGenerator(name = "permission_seq", sequenceName = "person.permissions_permission_id_seq", allocationSize = 1)
    @Column(name = "permission_id")
    private Short permissionId;

    @Column(name = "permission_type")
    private PermissionType permissionType;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private Role role;
}
