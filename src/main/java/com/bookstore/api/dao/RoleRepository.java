package com.bookstore.api.dao;

import com.bookstore.api.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
