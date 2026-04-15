package com.example.order_service.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    private long id;

    private long customerId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
