package com.bookstore.api.service;

import com.bookstore.api.entity.order.OrderTrack;

import java.util.List;

public interface OrderTrackService {

    List<OrderTrack> findAll();

    OrderTrack findById(int orderTrackId);
}
