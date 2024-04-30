package com.bookstore.api.service;

import com.bookstore.api.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(Integer id);
}
