package com.bookstore.api.controller;

import com.bookstore.api.dto.UserDTO;
import com.bookstore.api.entity.user.User;
import com.bookstore.api.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
public class LoginRestController {

    private final UserService userService;

    // quick and dirty: inject users dao (use constructor injection)
    public LoginRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/login")
    public UserDTO login(@RequestParam(required = false) String userName,
                                         @RequestParam(required = false) String password) {

        User user = userService.findByUsername(userName);

        UserDTO userDTO = new UserDTO();

        // Nếu không tìm thấy user, hoặc mật khẩu không khớp
        if (user == null || !Objects.equals(user.getPassword(), password)) {
            userDTO.setError(true);
            userDTO.setMessage("Invalid username or password");
            userDTO.setUser(null);
            return userDTO;
        }

        userDTO.setError(false);
        userDTO.setMessage("Login successfull");
        userDTO.setUser(user);

        return userDTO;
    }
}
