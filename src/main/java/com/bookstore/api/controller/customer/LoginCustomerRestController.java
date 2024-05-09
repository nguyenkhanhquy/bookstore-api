package com.bookstore.api.controller.customer;

import com.bookstore.api.dto.CustomerDTO;
import com.bookstore.api.entity.Customer;
import com.bookstore.api.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
public class LoginCustomerRestController {

    private final CustomerService customerService;

    // quick and dirty: inject customers dao (use constructor injection)
    public LoginCustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customers/login")
    public ResponseEntity<CustomerDTO> login(@RequestParam(required = false) String userName,
                                             @RequestParam(required = false) String password) {

        Customer customer = customerService.findByUsername(userName);

        CustomerDTO customerDTO = new CustomerDTO();

        // Nếu không tìm thấy nhân viên, hoặc mật khẩu không khớp
        if (customer == null || !Objects.equals(customer.getPassword(), password)) {
            customerDTO.setError(true);
            customerDTO.setMessage("Invalid username or password");
            customerDTO.setCustomer(null);
            return new ResponseEntity<>(customerDTO, HttpStatus.UNAUTHORIZED);
        }

        customerDTO.setError(false);
        customerDTO.setMessage("Login successfull");
        customerDTO.setCustomer(customer);

        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }
}
