package com.bookstore.api.repository;

import com.bookstore.api.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart findByUserId(int id);
}
