package com.codeid.usersmanagement.repository;

import com.codeid.usersmanagement.model.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Short> {
}
