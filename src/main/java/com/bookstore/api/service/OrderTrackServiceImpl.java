package com.bookstore.api.service;

import com.bookstore.api.entity.order.OrderTrack;
import com.bookstore.api.repository.OrderTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderTrackServiceImpl implements OrderTrackService {

    private final OrderTrackRepository orderTrackRepository;

    @Autowired
    public OrderTrackServiceImpl(OrderTrackRepository orderTrackRepository) {
        this.orderTrackRepository = orderTrackRepository;
    }

    @Override
    public List<OrderTrack> findAll() {
        return orderTrackRepository.findAll();
    }

    @Override
    public OrderTrack findById(int orderTrackId) {
        return orderTrackRepository.findById(orderTrackId).orElse(null);
    }
}
