package com.bookstore.api.repository;

import com.bookstore.api.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o WHERE o.user.id = :userId AND o.orderTrack.id = :orderTrackId")
    List<Order> findByUserIdAndOrderTrackId(@Param("userId") int userId, @Param("orderTrackId") int orderTrackId);

    List<Order> findByOrderTrackId(int orderTrackId);
}
