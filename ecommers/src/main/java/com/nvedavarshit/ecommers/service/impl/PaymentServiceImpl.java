package com.nvedavarshit.ecommers.service.impl;

import com.nvedavarshit.ecommers.dto.PaymentRequest;
import com.nvedavarshit.ecommers.dto.PaymentWebhookRequest;
import com.nvedavarshit.ecommers.model.Order;
import com.nvedavarshit.ecommers.model.Payment;
import com.nvedavarshit.ecommers.repository.OrderRepository;
import com.nvedavarshit.ecommers.repository.PaymentRepository;
import com.nvedavarshit.ecommers.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    @Override
    public Payment createPayment(PaymentRequest request) {

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getStatus().equals("CREATED")) {
            throw new RuntimeException("Order not eligible for payment");
        }

        Payment payment = new Payment();
        payment.setOrderId(order.getId());
        payment.setAmount(request.getAmount());
        payment.setStatus("PENDING");
        payment.setCreatedAt(Instant.now());

        payment = paymentRepository.save(payment);

        // 🔁 Call Mock Payment Service (async simulation)
        new Thread(() -> {
            try {
                Thread.sleep(3000);

                PaymentWebhookRequest webhook = new PaymentWebhookRequest();
                webhook.setOrderId(order.getId());
                webhook.setStatus("SUCCESS");

                restTemplate.postForEntity(
                        "http://localhost:8080/api/webhooks/payment",
                        webhook,
                        Void.class
                );

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        return payment;
    }

    @Override
    public void handleWebhook(PaymentWebhookRequest request) {

        Payment payment = paymentRepository.findByOrderId(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (request.getStatus().equals("SUCCESS")) {
            payment.setStatus("SUCCESS");
            order.setStatus("PAID");
        } else {
            payment.setStatus("FAILED");
            order.setStatus("FAILED");
        }

        paymentRepository.save(payment);
        orderRepository.save(order);
    }
}
