package com.rodrigoguzman.tt_supermarket.service;

import java.util.List;

import com.rodrigoguzman.tt_supermarket.model.Product;

public interface IProductService {
    List<Product> getAllProducts();

    Product getProductById(Long id);

    void createProduct(Product product);

    void updateProduct(Long id, Product product);

    void deleteProduct(Long id);
}