package com.example.orderservice.rest.dto;

import com.example.orderservice.domain.model.OrderStatus;

public record OrderResponse(
        long id,
        long customerId,
        OrderStatus status
) {
}
