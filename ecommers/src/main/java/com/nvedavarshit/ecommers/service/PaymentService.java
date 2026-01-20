package com.nvedavarshit.ecommers.service;

import com.nvedavarshit.ecommers.dto.PaymentRequest;
import com.nvedavarshit.ecommers.dto.PaymentWebhookRequest;
import com.nvedavarshit.ecommers.model.Payment;

public interface PaymentService {

    Payment createPayment(PaymentRequest request);

    void handleWebhook(PaymentWebhookRequest request);
}
