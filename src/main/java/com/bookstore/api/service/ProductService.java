package com.bookstore.api.service;

import com.bookstore.api.entity.product.Product;
import com.bookstore.api.response.ProductResponse;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    ProductResponse findById(Integer productId);

    ProductResponse save(Product theProduct);

    ProductResponse update(Integer productId, Product theProduct);

    ProductResponse deleteById(Integer productId);
}
