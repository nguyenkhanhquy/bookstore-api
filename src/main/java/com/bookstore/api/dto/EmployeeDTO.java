package com.bookstore.api.dto;

import com.bookstore.api.entity.Employee;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDTO {

    private boolean error;
    private String message;
    private Employee employee;
}
