package com.bookstore.api.controller;

import com.bookstore.api.response.UserResponse;
import com.bookstore.api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/login")
    public ResponseEntity<UserResponse> login(@RequestParam(required = false) String userName,
                                             @RequestParam(required = false) String password) {

        UserResponse response = userService.login(userName, password);
        if (response.isError()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
