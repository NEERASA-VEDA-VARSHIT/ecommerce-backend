package com.nvedavarshit.ecommers.service.impl;

import com.nvedavarshit.ecommers.dto.CreateOrderRequest;
import com.nvedavarshit.ecommers.model.CartItem;
import com.nvedavarshit.ecommers.model.Order;
import com.nvedavarshit.ecommers.model.OrderItem;
import com.nvedavarshit.ecommers.model.Product;
import com.nvedavarshit.ecommers.repository.CartRepository;
import com.nvedavarshit.ecommers.repository.OrderItemRepository;
import com.nvedavarshit.ecommers.repository.OrderRepository;
import com.nvedavarshit.ecommers.repository.ProductRepository;
import com.nvedavarshit.ecommers.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public Order createOrder(CreateOrderRequest request) {

        List<CartItem> cartItems = cartRepository.findByUserId(request.getUserId());

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        double totalAmount = 0;

        // 1. Validate stock + calculate total
        for (CartItem item : cartItems) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getStock() < item.getQuantity()) {
                throw new RuntimeException(
                        "Insufficient stock for product: " + product.getName());
            }

            totalAmount += product.getPrice() * item.getQuantity();
        }

        // 2. Create Order
        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setTotalAmount(totalAmount);
        order.setStatus("CREATED");
        order.setCreatedAt(Instant.now());

        order = orderRepository.save(order);

        // 3. Create OrderItems + reduce stock
        for (CartItem item : cartItems) {
            Product product = productRepository.findById(item.getProductId()).get();

            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getId());
            orderItem.setProductId(product.getId());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(product.getPrice());

            orderItemRepository.save(orderItem);

            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);
        }

        // 4. Clear Cart
        cartRepository.deleteByUserId(request.getUserId());

        return order;
    }

    @Override
    public Order getOrderById(String orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
