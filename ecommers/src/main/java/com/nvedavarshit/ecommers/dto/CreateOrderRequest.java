package com.nvedavarshit.ecommers.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateOrderRequest {

    @NotBlank
    private String userId;
}
