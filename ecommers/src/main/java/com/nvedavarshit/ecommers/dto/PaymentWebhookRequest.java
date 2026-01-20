package com.nvedavarshit.ecommers.dto;

import lombok.Data;

@Data
public class PaymentWebhookRequest {

    private String orderId;
    private String status; // SUCCESS or FAILED
}
