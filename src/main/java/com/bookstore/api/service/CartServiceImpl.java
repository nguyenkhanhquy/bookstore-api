package com.bookstore.api.service;

import com.bookstore.api.repository.CartRepository;
import com.bookstore.api.entity.cart.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart findById(Integer cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }

    @Override
    public Cart save(Cart theCart) {
        return cartRepository.save(theCart);
    }

    @Override
    public Cart findByUserId(int userId) {
        return cartRepository.findByUserId(userId);
    }
}
