package com.bookstore.api.controller;

import com.bookstore.api.entity.order.Order;
import com.bookstore.api.service.OrderService;
import com.bookstore.api.service.OrderTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderService orderService;
    private final OrderTrackService orderTrackService;

    @Autowired
    public OrderController(OrderService orderService, OrderTrackService orderTrackService) {
        this.orderService = orderService;
        this.orderTrackService = orderTrackService;
    }

    @GetMapping("/orders")
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @PostMapping("/orders")
    public Order save(@RequestBody Order order) {
        return orderService.save(order);
    }

    @PostMapping("/orders/add-order")
    public Order addOrder(@RequestBody Order order) {
        order.setOrderTrack(orderTrackService.findById(1));
        return orderService.save(order);
    }
}
