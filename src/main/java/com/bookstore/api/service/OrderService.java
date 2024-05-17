package com.bookstore.api.service;

import com.bookstore.api.dto.OrderDTO;
import com.bookstore.api.entity.order.Order;

import java.util.List;

public interface OrderService {

    List<Order> findAll();

    Order createOrder(OrderDTO orderDTO);

    List<Order> getOrdersByUserIdAndOrderTrackId(int userId, int orderTrackId);
}
