package com.bookstore.api.controller;

import com.bookstore.api.response.ProductResponse;
import com.bookstore.api.entity.product.Product;
import com.bookstore.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductResponse> findById(@PathVariable int productId) {

        ProductResponse response = productService.findById(productId);
        if (response.isError()) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody Product theProduct) {

        ProductResponse response = productService.save(theProduct);
        if (response.isError()) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable int productId,
                                                         @RequestBody Product theProduct) {

        ProductResponse response = productService.findById(productId);
        if (response.isError()) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response = productService.update(productId, theProduct);
        if (response.isError()) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<ProductResponse> deleteProduct(@PathVariable int productId) {

        ProductResponse response = productService.findById(productId);
        if (response.isError()) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response = productService.deleteById(productId);
        if (response.isError()) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
