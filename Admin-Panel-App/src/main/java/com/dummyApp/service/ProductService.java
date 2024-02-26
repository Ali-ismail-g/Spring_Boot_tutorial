package com.dummyApp.service;

import com.dummyApp.model.Product;
import com.dummyApp.model.ProductDetails;

import java.util.List;

public interface ProductService {

    ProductDetails insert(ProductDetails product);
    Product findById(int id);
    void deleteById(int id);
    ProductDetails update(ProductDetails product);
    List<Product> getAllProducts();
    List<Product> search(String name);
}
