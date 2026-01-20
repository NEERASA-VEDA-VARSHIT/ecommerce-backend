package com.nvedavarshit.ecommers.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cart_items")
@Data
@Getter
@Setter
public class CartItem {

    @Id
    private String id;
    private String userId;
    private String productId;
    private Integer quantity;
}
