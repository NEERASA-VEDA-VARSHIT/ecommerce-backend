package com.nvedavarshit.ecommers.repository;

import com.nvedavarshit.ecommers.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}

