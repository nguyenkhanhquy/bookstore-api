package com.bookstore.api.dao;

import com.bookstore.api.entity.cart.Cart;
import com.bookstore.api.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart findByUserId(int id);
}
