package com.nvedavarshit.ecommers.service.impl;

import com.nvedavarshit.ecommers.dto.AddToCartRequest;
import com.nvedavarshit.ecommers.dto.CartItemResponse;
import com.nvedavarshit.ecommers.model.CartItem;
import com.nvedavarshit.ecommers.model.Product;
import com.nvedavarshit.ecommers.repository.CartRepository;
import com.nvedavarshit.ecommers.repository.ProductRepository;
import com.nvedavarshit.ecommers.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    public CartItem addToCart(AddToCartRequest request) {

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStock() < request.getQuantity()) {
            throw new RuntimeException("Insufficient stock");
        }

        CartItem cartItem = cartRepository
                .findByUserIdAndProductId(request.getUserId(), request.getProductId())
                .orElseGet(() -> {
                    CartItem item = new CartItem();
                    item.setUserId(request.getUserId());
                    item.setProductId(request.getProductId());
                    item.setQuantity(0);
                    return item;
                });

        cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());

        return cartRepository.save(cartItem);
    }

    @Override
    public List<CartItemResponse> getUserCart(String userId) {

        List<CartItem> items = cartRepository.findByUserId(userId);

        return items.stream().map(item -> {
            Product product = productRepository
                    .findById(item.getProductId())
                    .orElse(null);

            return new CartItemResponse(
                    item.getId(),
                    item.getProductId(),
                    item.getQuantity(),
                    product
            );
        }).toList();
    }

    @Override
    public void clearCart(String userId) {
        cartRepository.deleteByUserId(userId);
    }
}
