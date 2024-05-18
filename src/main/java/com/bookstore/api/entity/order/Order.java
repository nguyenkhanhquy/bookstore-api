package com.bookstore.api.entity.order;

import com.bookstore.api.entity.user.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "customer_order")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_track_id")
    private OrderTrack orderTrack;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderItem> orderItems;

    private String date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
