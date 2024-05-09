package com.bookstore.api.controller.customer;

import com.bookstore.api.dto.CustomerDTO;
import com.bookstore.api.entity.Customer;
import com.bookstore.api.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerRestController {

    private final CustomerService customerService;

    // quick and dirty: inject customers dao (use constructor injection)
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // expose "/customers" and return a list of customers
    @GetMapping("/customers")
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    // add mapping for GET "/customers/{customerId}"
    @GetMapping("/customers/{customerId}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable int customerId) {

        Customer theCustomer = customerService.findById(customerId);

        CustomerDTO customerDTO = new CustomerDTO();

        // throw exception if null
        if (theCustomer == null) {
            customerDTO.setError(true);
            customerDTO.setMessage("customer id not found - " + customerId);
            return new ResponseEntity<>(customerDTO, HttpStatus.NOT_FOUND);
        }

        customerDTO.setError(false);
        customerDTO.setMessage("success");
        customerDTO.setCustomer(theCustomer);

        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    // add mapping for POST "/customers" - add new customer
    @PostMapping("/customers")
    public CustomerDTO addCustomer(@RequestBody Customer theCustomer) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update

        theCustomer.setId(0);

        Customer dbCustomer = customerService.save(theCustomer);

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setError(false);
        customerDTO.setMessage("added customer id - " + dbCustomer.getId());
        customerDTO.setCustomer(dbCustomer);

        return customerDTO;
    }

    // add mapping for PUT "/customers" - update existing customer
    @PutMapping("/customers/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable int customerId,
                                                      @RequestBody Customer customer) {

        Customer theCustomer = customerService.findById(customerId);

        CustomerDTO customerDTO = new CustomerDTO();

        // throw exception if null
        if (theCustomer == null) {
            customerDTO.setError(true);
            customerDTO.setMessage("customer id not found - " + customerId);
            return new ResponseEntity<>(customerDTO, HttpStatus.NOT_FOUND);
        }

        theCustomer.setUserName(customer.getUserName());
        theCustomer.setFName(customer.getFName());
        theCustomer.setEmail(customer.getEmail());
        theCustomer.setGender(customer.getGender());
        theCustomer.setImages(customer.getImages());
        theCustomer.setPassword(customer.getPassword());

        Customer dbCustomer = customerService.save(theCustomer);

        customerDTO.setError(false);
        customerDTO.setMessage("success");
        customerDTO.setCustomer(dbCustomer);

        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    // add mapping for DELETE "/customers/{customerId}" - delete customer
    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<CustomerDTO> deleteCustomer(@PathVariable int customerId) {

        Customer theCustomer = customerService.findById(customerId);

        CustomerDTO customerDTO = new CustomerDTO();

        // throw exception if null
        if (theCustomer == null) {
            customerDTO.setError(true);
            customerDTO.setMessage("customer id not found - " + customerId);
            return new ResponseEntity<>(customerDTO, HttpStatus.NOT_FOUND);
        }

        customerService.deleteById(customerId);

        customerDTO.setError(false);
        customerDTO.setMessage("deleted customer id - " + customerId);
        customerDTO.setCustomer(theCustomer);

        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }
}
