package com.bookstore.api.entity.product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "images")
    private String images;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "price")
    private double price;
}
