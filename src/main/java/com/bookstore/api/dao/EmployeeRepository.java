package com.bookstore.api.dao;

import com.bookstore.api.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByUserName(String userName);
}
