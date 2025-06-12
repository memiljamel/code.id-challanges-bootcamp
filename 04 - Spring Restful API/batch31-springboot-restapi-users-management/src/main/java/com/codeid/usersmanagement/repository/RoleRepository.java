package com.codeid.usersmanagement.repository;

import com.codeid.usersmanagement.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Short> {
}
