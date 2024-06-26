package com.bookstore.api.entity.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Column(name = "quantity")
    private int quantity;

    private double price;

    @ManyToOne
    @JoinColumn(name = "customer_order_id")
    @JsonBackReference
    private Order order;
}
