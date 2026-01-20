package com.nvedavarshit.ecommers.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "payments")
@Data
@Getter
@Setter
public class Payment {

    @Id
    private String id;
    private String orderId;
    private Double amount;
    private String status;
    private String paymentId;
    private Instant createdAt;
}
