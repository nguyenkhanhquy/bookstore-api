package com.bookstore.api.controller;

import com.bookstore.api.dto.EmployeeDTO;
import com.bookstore.api.entity.Employee;
import com.bookstore.api.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class LoginRestController {

    private final EmployeeService employeeService;

    // quick and dirty: inject employees dao (use constructor injection)
    public LoginRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/login")
    public EmployeeDTO login(@RequestParam("userName") String userName,
                             @RequestParam("password") String password) {

        Employee employee = employeeService.findByUsername(userName);

        EmployeeDTO employeeDTO = new EmployeeDTO();

        // Nếu không tìm thấy nhân viên, hoặc mật khẩu không khớp
        if (employee == null || !employee.getPassword().equals(password)) {
            employeeDTO.setError(true);
            employeeDTO.setMessage("Invalid username or password");
            employeeDTO.setEmployee(null);
            return employeeDTO;
        }

        employeeDTO.setError(false);
        employeeDTO.setMessage("Login successfull");
        employeeDTO.setEmployee(employee);

        return employeeDTO;
    }
}
