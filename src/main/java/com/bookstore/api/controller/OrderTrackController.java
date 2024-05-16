package com.bookstore.api.controller;

import com.bookstore.api.entity.order.OrderTrack;
import com.bookstore.api.service.OrderTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderTrackController {

    private final OrderTrackService orderTrackService;

    @Autowired
    public OrderTrackController(OrderTrackService orderTrackService) {
        this.orderTrackService = orderTrackService;
    }

    @GetMapping("/ordertracks")
    public List<OrderTrack> findAll() {
        return orderTrackService.findAll();
    }
}
