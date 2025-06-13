package com.codeid.usersmanagement.repository;

import com.codeid.usersmanagement.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Short> {
}
