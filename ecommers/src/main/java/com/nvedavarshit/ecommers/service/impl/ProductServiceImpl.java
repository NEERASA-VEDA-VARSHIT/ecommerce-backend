package com.nvedavarshit.ecommers.service.impl;

import com.nvedavarshit.ecommers.dto.ProductRequest;
import com.nvedavarshit.ecommers.model.Product;
import com.nvedavarshit.ecommers.repository.ProductRepository;
import com.nvedavarshit.ecommers.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}

