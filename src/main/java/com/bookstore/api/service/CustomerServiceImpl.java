package com.bookstore.api.service;

import com.bookstore.api.dao.CustomerRepository;
import com.bookstore.api.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Integer id) {
        Optional<Customer> result = customerRepository.findById(id);

        Customer theCustomer;

        if (result.isPresent()) {
            theCustomer = result.get();
        }
        else {
            // we didn't find the employee
            // throw new RuntimeException("Did not find employee id - " + id);
            theCustomer = null;
        }
        // theEmployee = result.orElse(null);

        return theCustomer;
    }

    @Override
    public Customer save(Customer theEmployee) {
        return customerRepository.save(theEmployee);
    }

    @Override
    public void deleteById(Integer id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer findByUsername(String userName) {
        return customerRepository.findByUserName(userName);
    }
}
