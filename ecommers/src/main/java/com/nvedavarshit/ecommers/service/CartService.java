package com.nvedavarshit.ecommers.service;

import com.nvedavarshit.ecommers.dto.AddToCartRequest;
import com.nvedavarshit.ecommers.dto.CartItemResponse;
import com.nvedavarshit.ecommers.model.CartItem;

import java.util.List;

public interface CartService {

    CartItem addToCart(AddToCartRequest request);

    List<CartItemResponse> getUserCart(String userId);

    void clearCart(String userId);
}

