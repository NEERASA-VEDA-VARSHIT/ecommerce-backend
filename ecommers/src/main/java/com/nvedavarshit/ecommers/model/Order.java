package com.nvedavarshit.ecommers.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "orders")
@Data
@Getter
@Setter
public class Order {

    @Id
    private String id;
    private String userId;
    private Double totalAmount;
    private String status;
    private Instant createdAt;
}

