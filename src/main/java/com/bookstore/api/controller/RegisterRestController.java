package com.bookstore.api.controller;

import com.bookstore.api.dto.UserDTO;
import com.bookstore.api.entity.cart.Cart;
import com.bookstore.api.entity.user.User;
import com.bookstore.api.service.CartService;
import com.bookstore.api.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class RegisterRestController {

    private final UserService userService;
    private final CartService cartService;

    public RegisterRestController(UserService userService, CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    @PostMapping("/users/register")
    public UserDTO login(@RequestBody User theUser) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update

        UserDTO userDTO = new UserDTO();

        if (userService.existsByUsername(theUser.getUserName())) {
            userDTO.setError(true);
            userDTO.setMessage("Username already exists");
        } else if (userService.existsByEmail(theUser.getEmail())) {
            userDTO.setError(true);
            userDTO.setMessage("Email already exists");
        } else {
            theUser.setId(0);

            User dbUser = userService.save(theUser);
            Cart cart = new Cart();
            cart.setUser(dbUser);
            cartService.save(cart);

            userDTO.setError(false);
            userDTO.setMessage("Register successfull");
            userDTO.setUser(dbUser);
        }

        return userDTO;
    }
}
