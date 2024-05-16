package com.bookstore.api.service;

import com.bookstore.api.entity.order.Order;

import java.util.List;

public interface OrderService {

    List<Order> findAll();

    Order save(Order order);
}
