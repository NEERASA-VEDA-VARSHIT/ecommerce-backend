package com.nvedavarshit.ecommers.repository;

import com.nvedavarshit.ecommers.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
