package com.nvedavarshit.ecommers.webhook;

import com.nvedavarshit.ecommers.dto.PaymentWebhookRequest;
import com.nvedavarshit.ecommers.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/webhooks")
@RequiredArgsConstructor
public class PaymentWebhookController {

    private final PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<String> handlePaymentWebhook(
            @RequestBody PaymentWebhookRequest request) {

        paymentService.handleWebhook(request);
        return ResponseEntity.ok("Webhook processed");
    }
}
