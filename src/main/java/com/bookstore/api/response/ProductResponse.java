package com.bookstore.api.response;

import com.bookstore.api.entity.product.Product;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductResponse {

    private boolean error;
    private String message;
    private Product product;
}
