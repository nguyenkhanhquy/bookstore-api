package com.bookstore.api.controller;

import com.bookstore.api.response.UserResponse;
import com.bookstore.api.entity.cart.Cart;
import com.bookstore.api.entity.user.User;
import com.bookstore.api.service.CartService;
import com.bookstore.api.service.RoleService;
import com.bookstore.api.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class RegisterController {

    private final UserService userService;
    private final CartService cartService;
    private final RoleService roleService;

    public RegisterController(UserService userService, CartService cartService, RoleService roleService) {
        this.userService = userService;
        this.cartService = cartService;
        this.roleService = roleService;
    }

    @PostMapping("/users/register")
    public UserResponse login(@RequestBody User theUser) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update

        UserResponse userResponse = new UserResponse();

        if (userService.existsByUsername(theUser.getUserName())) {
            userResponse.setError(true);
            userResponse.setMessage("Username already exists");
        } else if (userService.existsByEmail(theUser.getEmail())) {
            userResponse.setError(true);
            userResponse.setMessage("Email already exists");
        } else if (userService.existsByPhone(theUser.getPhone())) {
            userResponse.setError(true);
            userResponse.setMessage("Phone already exists");
        } else {
            theUser.setId(0);
            theUser.setRole(roleService.findById(2));
            theUser.setImages("https://book-store-upload.s3.amazonaws.com/user-images/default-images.png");

            User dbUser = userService.save(theUser);
            Cart cart = new Cart();
            cart.setUser(dbUser);
            cartService.save(cart);

            userResponse.setError(false);
            userResponse.setMessage("Register successfully");
            userResponse.setUser(dbUser);
        }

        return userResponse;
    }
}
