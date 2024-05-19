package com.bookstore.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO {

    private String nameProduct;
    private int quantity;
    private double price;
}
