package com.bookstore.api.service;

import com.bookstore.api.entity.user.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Integer id);

    User save(User theUser);

    void deleteById(Integer id);

    User findByUsername(String userName);
}
