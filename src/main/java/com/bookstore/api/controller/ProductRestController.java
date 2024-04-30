package com.bookstore.api.controller;

import com.bookstore.api.dto.ProductDTO;
import com.bookstore.api.entity.Product;
import com.bookstore.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductRestController {

    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/products/{productId}")
    public ProductDTO findById(@PathVariable int productId) {

        Product product = productService.findById(productId);

        if (product == null) {
            return new ProductDTO(true, "not found", null);
        }

        return new ProductDTO(true, "found", product);
    }
}
