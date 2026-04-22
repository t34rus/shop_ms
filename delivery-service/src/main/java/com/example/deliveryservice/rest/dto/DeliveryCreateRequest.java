package com.example.deliveryservice.rest.dto;

public record DeliveryCreateRequest(
        long id,
        long orderId,
        String address
) {
}
