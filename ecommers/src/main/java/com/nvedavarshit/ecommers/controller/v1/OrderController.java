package com.nvedavarshit.ecommers.controller.v1;

import com.nvedavarshit.ecommers.dto.CreateOrderRequest;
import com.nvedavarshit.ecommers.model.Order;
import com.nvedavarshit.ecommers.model.OrderItem;
import com.nvedavarshit.ecommers.model.Payment;
import com.nvedavarshit.ecommers.repository.OrderItemRepository;
import com.nvedavarshit.ecommers.repository.PaymentRepository;
import com.nvedavarshit.ecommers.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderItemRepository orderItemRepository;
    private final PaymentRepository paymentRepository;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createOrder(
            @Valid @RequestBody CreateOrderRequest request) {

        Order order = orderService.createOrder(request);
        List<OrderItem> items = orderItemRepository.findByOrderId(order.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of(
                        "id", order.getId(),
                        "userId", order.getUserId(),
                        "totalAmount", order.getTotalAmount(),
                        "status", order.getStatus(),
                        "items", items
                )
        );
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Map<String, Object>> getOrder(
            @PathVariable String orderId) {

        Order order = orderService.getOrderById(orderId);
        List<OrderItem> items = orderItemRepository.findByOrderId(orderId);
        Payment payment = paymentRepository.findByOrderId(orderId).orElse(null);

        return ResponseEntity.ok(
                Map.of(
                        "id", order.getId(),
                        "userId", order.getUserId(),
                        "totalAmount", order.getTotalAmount(),
                        "status", order.getStatus(),
                        "items", items,
                        "payment", payment
                )
        );
    }
}
