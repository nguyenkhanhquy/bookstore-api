package com.bookstore.api.controller;

import com.bookstore.api.response.ProductResponse;
import com.bookstore.api.entity.product.Product;
import com.bookstore.api.service.ProductService;
import com.bookstore.api.util.S3Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public List<Product> getProducts() {
        return productService.findAll();
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable int productId) {

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

    @PostMapping("/products/add-product")
    public ResponseEntity<ProductResponse> addProduct(@RequestParam("name") String name,
                                                      @RequestParam("description") String description,
                                                      @RequestParam("price") Double price,
                                                      @RequestParam("images") MultipartFile multipart) {

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        ProductResponse response = productService.save(product);
        if (response.isError()) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Product theProduct = response.getProduct();

        try {
            String fileName = multipart.getOriginalFilename();
            assert fileName != null;
            String newFileName = S3Util.urlFolderProduct + theProduct.getId() + fileName.substring(fileName.lastIndexOf('.'));
            S3Util.uploadFile(newFileName, multipart.getInputStream());
            String urlImage = "https://" + S3Util.bucketName + ".s3.amazonaws.com/" + newFileName;

            theProduct.setImages(urlImage);
            response = productService.update(theProduct.getId(), theProduct);
            response.setMessage("Added product id - " + theProduct.getId());
        } catch (IOException ex) {
            response.setError(true);
            response.setMessage("Error uploading file: " + ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/products/update-images")
    public ResponseEntity<ProductResponse> updateImages(@RequestParam("id") int productId,
                                                        @RequestParam("images") MultipartFile multipart) {

        ProductResponse response = productService.findById(productId);
        if (response.isError()) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        Product theProduct = response.getProduct();

        try {
            String fileName = multipart.getOriginalFilename();
            assert fileName != null;
            String newFileName = S3Util.urlFolderProduct + productId + fileName.substring(fileName.lastIndexOf('.'));
            S3Util.uploadFile(newFileName, multipart.getInputStream());
            String urlImage = "https://" + S3Util.bucketName + ".s3.amazonaws.com/" + newFileName;

            theProduct.setImages(urlImage);
            response = productService.update(productId, theProduct);
            response.setMessage("Update images successfully");
            response.setProduct(theProduct);
        } catch (IOException ex) {
            response.setError(true);
            response.setMessage("Error uploading file: " + ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
