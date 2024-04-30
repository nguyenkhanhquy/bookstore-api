package com.bookstore.api.dao;

import com.bookstore.api.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it ... no need to write any code
    // findAll(), findById(), save(), deleteById(), ...
    Employee findByUserName(String username);
}
