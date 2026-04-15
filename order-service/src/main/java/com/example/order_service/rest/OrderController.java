package com.example.order_service.rest;

import com.example.order_service.domain.model.Order;
import com.example.order_service.domain.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderRepository orderRepository;

    @GetMapping
    public List<Order> orders() {
        return orderRepository.findAll();
    }
}
