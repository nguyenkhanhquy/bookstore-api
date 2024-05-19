package com.bookstore.api.repository;

import com.bookstore.api.entity.order.OrderTrack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderTrackRepository extends JpaRepository<OrderTrack, Integer> {

}
