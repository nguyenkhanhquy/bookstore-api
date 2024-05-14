package com.bookstore.api.service;

import com.bookstore.api.entity.user.User;
import com.bookstore.api.response.UserResponse;

import java.util.List;

public interface UserService {

    List<User> findAll();

    UserResponse findById(Integer id);

    User save(User theUser);

    void deleteById(Integer id);

    UserResponse login(String userName, String password);

    UserResponse checkInfo(User theUser);

    UserResponse updateInfo(User theUser);
}
