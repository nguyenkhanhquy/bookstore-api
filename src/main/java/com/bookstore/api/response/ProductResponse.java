package com.bookstore.api.response;

import com.bookstore.api.entity.product.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {

    private boolean error;
    private String message;
    private Product product;
}
