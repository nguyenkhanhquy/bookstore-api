package com.bookstore.api.service;

import com.bookstore.api.dao.UserRepository;
import com.bookstore.api.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        Optional<User> result = userRepository.findById(id);

        User theUser;

        if (result.isPresent()) {
            theUser = result.get();
        }
        else {
            // we didn't find the user
            // throw new RuntimeException("Did not find employee id - " + id);
            theUser = null;
        }
        // theUser = result.orElse(null);

        return theUser;
    }

    @Override
    public User save(User theUser) {
        return userRepository.save(theUser);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public boolean existsByUsername(String userName) {
        return userRepository.existsByUserName(userName);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }
}
