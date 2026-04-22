package com.example.deliveryservice.rest.dto;

public record DeliveryResponse(
        long id,
        long orderId,
        String address
) {
}
