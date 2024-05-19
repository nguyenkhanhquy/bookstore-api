package com.bookstore.api.service;

import com.bookstore.api.entity.product.Product;
import com.bookstore.api.repository.ProductRepository;
import com.bookstore.api.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductResponse findById(Integer productId) {

        ProductResponse response = new ProductResponse();
        Product product = productRepository.findById(productId).orElse(null);

        if (product == null) {
            response.setError(true);
            response.setMessage("Not found product id - " + productId);
        } else {
            response.setError(false);
            response.setMessage("Found product id - " + productId);
            response.setProduct(product);
        }

        return response;
    }

    @Override
    public ProductResponse save(Product theProduct) {

        ProductResponse response = new ProductResponse();
        try {
            theProduct.setId(0);
            response.setProduct(productRepository.save(theProduct));
            response.setError(false);
            response.setMessage("Added product id - " + theProduct.getId());
        } catch (Exception e) {
            response.setError(true);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public ProductResponse update(Integer productId, Product theProduct) {

        ProductResponse response = new ProductResponse();
        try {
            theProduct.setId(productId);
            response.setProduct(productRepository.save(theProduct));
            response.setError(false);
            response.setMessage("Updated product id - " + theProduct.getId());
        } catch (Exception e) {
            response.setError(true);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public ProductResponse deleteById(Integer productId) {

        ProductResponse response = new ProductResponse();
        try {
            productRepository.deleteById(productId);
            response.setError(false);
            response.setMessage("Deleted product id - " + productId);
        } catch (Exception e) {
            response.setError(true);
            response.setMessage(e.getMessage());
        }

        return response;
    }
}
