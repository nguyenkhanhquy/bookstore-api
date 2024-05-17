package com.bookstore.api.controller;

import com.bookstore.api.dto.OrderDTO;
import com.bookstore.api.entity.order.Order;
import com.bookstore.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = orderService.createOrder(orderDTO);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/orders/filter")
    public ResponseEntity<List<Order>> getOrdersByUserIdAndOrderTrackId(
            @RequestParam int userId,
            @RequestParam int orderTrackId) {
        List<Order> orders = orderService.getOrdersByUserIdAndOrderTrackId(userId, orderTrackId);
        return ResponseEntity.ok(orders);
    }
}
