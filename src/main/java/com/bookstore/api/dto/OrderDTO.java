package com.bookstore.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDTO {

    private int orderTrackId;
    private int userId;
    private List<OrderItemDTO> orderItems;
}
