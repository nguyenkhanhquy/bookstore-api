package com.bookstore.api.service;

import com.bookstore.api.repository.RoleRepository;
import com.bookstore.api.entity.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(int roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }
}
