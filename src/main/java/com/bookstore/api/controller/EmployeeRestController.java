package com.bookstore.api.controller;

import com.bookstore.api.dto.EmployeeDTO;
import com.bookstore.api.entity.Employee;
import com.bookstore.api.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    // quick and dirty: inject employees dao (use constructor injection)
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public EmployeeDTO findAll() {

        List<Employee> employees = employeeService.findAll();

        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setError(false);
        employeeDTO.setMessage("success");
        employeeDTO.setResult(employees);

        return employeeDTO;
    }

    // add mapping for GET "/employees/{employeeId}"
    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<EmployeeDTO> findById(@PathVariable int employeeId) {

        Employee theEmployee = employeeService.findById(employeeId);

        EmployeeDTO employeeDTO = new EmployeeDTO();

        // throw exception if null
        if (theEmployee == null) {
            employeeDTO.setError(true);
            employeeDTO.setMessage("employee id not found - " + employeeId);
            employeeDTO.setResult(Collections.emptyList());
            return new ResponseEntity<>(employeeDTO, HttpStatus.NOT_FOUND);
        }

        employeeDTO.setError(false);
        employeeDTO.setMessage("success");
        employeeDTO.setResult(Collections.singletonList(theEmployee));

        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    // add mapping for POST "/employees" - add new employee
    @PostMapping("/employees")
    public EmployeeDTO addEmployee(@RequestBody Employee theEmployee) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update

        theEmployee.setId(0);

        Employee dbEmployee = employeeService.save(theEmployee);

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setError(false);
        employeeDTO.setMessage("added employee id - " + dbEmployee.getId());
        employeeDTO.setResult(Collections.singletonList(dbEmployee));

        return employeeDTO;
    }

    // add mapping for PUT "/employees" - update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    // add mapping for DELETE "/employees/{employeeId}" - delete employee
    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<EmployeeDTO> deleteEmployee(@PathVariable int employeeId) {

        Employee theEmployee = employeeService.findById(employeeId);

        EmployeeDTO employeeDTO = new EmployeeDTO();

        // throw exception if null
        if (theEmployee == null) {
            employeeDTO.setError(true);
            employeeDTO.setMessage("employee id not found - " + employeeId);
            employeeDTO.setResult(Collections.emptyList());
            return new ResponseEntity<>(employeeDTO, HttpStatus.NOT_FOUND);
        }

        employeeService.deleteById(employeeId);

        employeeDTO.setError(false);
        employeeDTO.setMessage("deleted employee id - " + employeeId);
        employeeDTO.setResult(Collections.singletonList(theEmployee));

        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }
}
