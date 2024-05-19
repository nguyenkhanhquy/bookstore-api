package com.bookstore.api.service;

import com.bookstore.api.entity.user.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findById(int roleId);
}
