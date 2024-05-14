package com.bookstore.api.service;

import com.bookstore.api.repository.UserRepository;
import com.bookstore.api.entity.user.User;
import com.bookstore.api.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public UserResponse findById(Integer id) {

        UserResponse response = new UserResponse();
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            response.setError(true);
            response.setMessage("User not found");
        } else {
            response.setError(false);
            response.setMessage("User found");
            response.setUser(user);
        }

        return response;
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
    public UserResponse login(String userName, String password) {

        UserResponse response = new UserResponse();
        User user = userRepository.findByUserName(userName);
        if (user == null || !Objects.equals(user.getPassword(), password)) {
            response.setError(true);
            response.setMessage("Invalid username or password");
            return response;
        }

        response.setError(false);
        response.setMessage("Login successfully");
        response.setUser(user);
        return response;
    }

    @Override
    public UserResponse checkInfo(User theUser) {

        UserResponse response = new UserResponse();
        if (userRepository.existsByUserName(theUser.getUserName())) {
            response.setError(true);
            response.setMessage("Username already exists");
        } else if (userRepository.existsByEmail(theUser.getEmail())) {
            response.setError(true);
            response.setMessage("Email already exists");
        } else if (userRepository.existsByPhone(theUser.getPhone())) {
            response.setError(true);
            response.setMessage("Phone already exists");
        } else {
            response.setError(false);
            response.setMessage("Info is not duplicated");
        }

        return response;
    }

    @Override
    public UserResponse updateInfo(User theUser) {

        UserResponse response = new UserResponse();
        try {
            User dbUser = userRepository.save(theUser);
            response.setError(false);
            response.setMessage("Update info successfully");
            response.setUser(dbUser);
        } catch (Exception e) {
            response.setError(true);
            response.setMessage("Error updating info");
        }
        return response;
    }
}
