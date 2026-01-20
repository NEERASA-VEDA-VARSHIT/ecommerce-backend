package com.nvedavarshit.ecommers.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order_items")
@Data
@Getter
@Setter
public class OrderItem {

    @Id
    private String id;
    private String orderId;
    private String productId;
    private Integer quantity;
    private Double price;
}

