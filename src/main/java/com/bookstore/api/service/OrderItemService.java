package com.bookstore.api.service;

import com.bookstore.api.entity.order.OrderItem;

import java.util.List;

public interface OrderItemService {

    List<OrderItem> findAll();

    OrderItem save(OrderItem orderItem);
}
