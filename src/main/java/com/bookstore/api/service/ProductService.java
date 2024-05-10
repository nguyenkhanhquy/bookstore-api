package com.bookstore.api.service;

import com.bookstore.api.entity.product.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(Integer id);
}
