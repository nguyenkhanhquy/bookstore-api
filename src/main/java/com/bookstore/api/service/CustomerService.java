package com.bookstore.api.service;

import com.bookstore.api.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Customer findById(Integer id);

    Customer save(Customer theCustomer);

    void deleteById(Integer id);

    Customer findByUsername(String userName);
}
