package com.bookstore.api.entity.order;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_track")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
}
