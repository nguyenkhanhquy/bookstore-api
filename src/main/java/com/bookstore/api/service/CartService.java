package com.bookstore.api.service;

import com.bookstore.api.entity.cart.Cart;

import java.util.List;

public interface CartService {

    List<Cart> findAll();

    Cart findById(Integer cartId);

    Cart save(Cart theCart);

    Cart findByUserId(int userId);
}
