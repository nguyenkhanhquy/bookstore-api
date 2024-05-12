package com.bookstore.api.controller;

import com.bookstore.api.response.UserResponse;
import com.bookstore.api.entity.cart.Cart;
import com.bookstore.api.entity.user.User;
import com.bookstore.api.service.CartService;
import com.bookstore.api.service.RoleService;
import com.bookstore.api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserResponse> register(@RequestBody User theUser) {

        UserResponse response = userService.checkInfo(theUser);
        if (response.isError()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        theUser.setId(0);
        theUser.setRole(roleService.findById(1));
        theUser.setImages("https://book-store-upload.s3.amazonaws.com/user-images/default-images.png");

        User dbUser = userService.save(theUser);
        Cart cart = new Cart();
        cart.setUser(dbUser);
        cartService.save(cart);

        response.setError(false);
        response.setMessage("Register successfully");
        response.setUser(dbUser);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
