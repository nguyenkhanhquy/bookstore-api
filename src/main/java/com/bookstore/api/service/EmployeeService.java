package com.bookstore.api.service;

import com.bookstore.api.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(Integer id);

    Employee save(Employee theEmployee);

    void deleteById(Integer id);

    Employee findByUsername(String userName);
}
