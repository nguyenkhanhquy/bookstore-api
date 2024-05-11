package com.bookstore.api.controller;

import com.bookstore.api.response.UserResponse;
import com.bookstore.api.entity.user.User;
import com.bookstore.api.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
public class LoginController {

    private final UserService userService;

    // quick and dirty: inject users dao (use constructor injection)
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/login")
    public UserResponse login(@RequestParam(required = false) String userName,
                              @RequestParam(required = false) String password) {

        User user = userService.findByUsername(userName);

        UserResponse userResponse = new UserResponse();

        // Nếu không tìm thấy user, hoặc mật khẩu không khớp
        if (user == null || !Objects.equals(user.getPassword(), password)) {
            userResponse.setError(true);
            userResponse.setMessage("Invalid username or password");
            userResponse.setUser(null);
            return userResponse;
        }

        userResponse.setError(false);
        userResponse.setMessage("Login successfully");
        userResponse.setUser(user);

        return userResponse;
    }
}
