package com.nvedavarshit.ecommers.dto;

import com.nvedavarshit.ecommers.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemResponse {

    private String id;
    private String productId;
    private Integer quantity;
    private Product product;
}

