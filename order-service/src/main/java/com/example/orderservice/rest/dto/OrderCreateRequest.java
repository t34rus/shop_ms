package com.example.orderservice.rest.dto;

public record OrderCreateRequest(
        long id,
        long customerId) {
}
