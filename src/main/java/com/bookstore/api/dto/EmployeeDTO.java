package com.bookstore.api.dto;

import com.bookstore.api.entity.Employee;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDTO {

    private boolean error;
    private String message;
    private List<Employee> result;
}
