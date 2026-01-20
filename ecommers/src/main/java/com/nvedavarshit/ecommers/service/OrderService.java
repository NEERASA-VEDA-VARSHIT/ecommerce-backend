package com.nvedavarshit.ecommers.service;

import com.nvedavarshit.ecommers.dto.CreateOrderRequest;
import com.nvedavarshit.ecommers.model.Order;

public interface OrderService {

    Order createOrder(CreateOrderRequest request);

    Order getOrderById(String orderId);
}
