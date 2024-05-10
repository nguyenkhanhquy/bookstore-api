package com.bookstore.api.entity.order;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_item")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_product")
    private String nameProduct;

    @Column(name = "size")
    private String size;

    @Column(name = "quantity")
    private int quantity = 1;

    private double price;

    @ManyToOne
    @JoinColumn(name = "customer_order_id", nullable = false)
    private Order order;

    public double getTotal() {
        return price * quantity;
    }
}
