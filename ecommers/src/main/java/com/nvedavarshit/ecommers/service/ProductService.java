package com.nvedavarshit.ecommers.service;

import com.nvedavarshit.ecommers.dto.ProductRequest;
import com.nvedavarshit.ecommers.model.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(ProductRequest request);

    List<Product> getAllProducts();
}