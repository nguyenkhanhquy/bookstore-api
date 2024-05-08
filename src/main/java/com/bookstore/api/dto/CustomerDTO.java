package com.bookstore.api.dto;

import com.bookstore.api.entity.Customer;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomerDTO {

    private boolean error;
    private String message;
    private Customer customer;
}
