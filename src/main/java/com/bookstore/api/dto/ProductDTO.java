package com.bookstore.api.dto;

import com.bookstore.api.entity.Product;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductDTO {

    private boolean error;
    private String message;
    private Product product;
}
