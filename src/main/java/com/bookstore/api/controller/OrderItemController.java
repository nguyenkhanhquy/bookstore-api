package com.bookstore.api.controller;

import com.bookstore.api.entity.order.OrderItem;
import com.bookstore.api.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/orderitems")
    public List<OrderItem> getOrderItems() {
        return orderItemService.findAll();
    }

    @PostMapping("/orderitems")
    public OrderItem save(@RequestBody OrderItem orderItem) {
        return orderItemService.save(orderItem);
    }
}
