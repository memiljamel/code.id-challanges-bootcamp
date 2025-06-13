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
public class Permission extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permission_seq")
    @SequenceGenerator(name = "permission_seq", sequenceName = "person.permissions_permission_id_seq", allocationSize = 1)
    @Column(name = "permission_id")
    private Short permissionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "permission_type", length = 25)
    private PermissionType permissionType;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private Role role;
}
