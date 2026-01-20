package com.nvedavarshit.ecommers.controller.v1;

import com.nvedavarshit.ecommers.dto.AddToCartRequest;
import com.nvedavarshit.ecommers.dto.CartItemResponse;
import com.nvedavarshit.ecommers.model.CartItem;
import com.nvedavarshit.ecommers.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartItem> addToCart(
            @Valid @RequestBody AddToCartRequest request) {

        return ResponseEntity.ok(cartService.addToCart(request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItemResponse>> getCart(
            @PathVariable String userId) {

        return ResponseEntity.ok(cartService.getUserCart(userId));
    }

    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<Map<String, String>> clearCart(
            @PathVariable String userId) {

        cartService.clearCart(userId);
        return ResponseEntity.ok(
                Map.of("message", "Cart cleared successfully")
        );
    }
}
