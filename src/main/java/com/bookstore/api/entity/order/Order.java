package com.bookstore.api.entity.order;

import com.bookstore.api.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public double getTotal() {
        double total = 0.0;
        for (OrderItem item : orderItems) {
            total += item.getTotal();
        }
        return total;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }
}
