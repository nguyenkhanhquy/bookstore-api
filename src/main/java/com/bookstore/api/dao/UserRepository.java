package com.bookstore.api.dao;

import com.bookstore.api.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserName(String userName);

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);
}
