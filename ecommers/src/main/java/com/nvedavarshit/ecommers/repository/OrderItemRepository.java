package com.nvedavarshit.ecommers.repository;

import com.nvedavarshit.ecommers.model.OrderItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderItemRepository extends MongoRepository<OrderItem, String> {

    List<OrderItem> findByOrderId(String orderId);
}
