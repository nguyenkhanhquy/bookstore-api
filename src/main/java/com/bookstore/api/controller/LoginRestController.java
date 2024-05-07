package com.bookstore.api.controller;

import com.bookstore.api.dto.EmployeeDTO;
import com.bookstore.api.entity.Employee;
import com.bookstore.api.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
public class LoginRestController {

    private final EmployeeService employeeService;

    // quick and dirty: inject employees dao (use constructor injection)
    public LoginRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees/login")
    public ResponseEntity<EmployeeDTO> login(@RequestParam(required = false) String userName,
                                             @RequestParam(required = false) String password) {

        Employee employee = employeeService.findByUsername(userName);

        EmployeeDTO employeeDTO = new EmployeeDTO();

        // Nếu không tìm thấy nhân viên, hoặc mật khẩu không khớp
        if (employee == null || !Objects.equals(employee.getPassword(), password)) {
            employeeDTO.setError(true);
            employeeDTO.setMessage("Invalid username or password");
            employeeDTO.setEmployee(null);
            return new ResponseEntity<>(employeeDTO, HttpStatus.UNAUTHORIZED);
        }

        employeeDTO.setError(false);
        employeeDTO.setMessage("Login successfull");
        employeeDTO.setEmployee(employee);

        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }
}
