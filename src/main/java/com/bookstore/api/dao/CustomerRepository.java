package com.bookstore.api.dao;

import com.bookstore.api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByUserName(String userName);
}
