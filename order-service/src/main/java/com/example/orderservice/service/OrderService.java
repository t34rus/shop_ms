package com.example.orderservice.service;

import com.example.orderservice.domain.model.Order;
import com.example.orderservice.domain.repository.OrderRepository;
import com.example.orderservice.rest.dto.OrderCreateRequest;
import com.example.orderservice.rest.dto.OrderMapper;
import com.example.orderservice.rest.dto.OrderResponse;
import com.example.orderservice.rest.dto.OrderStatusUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    public OrderResponse findById(long id) {
        return mapper.toResponse(findOrderOrThrow(id));
    }

    public OrderResponse create(OrderCreateRequest request) {
        var order = mapper.toNewEntity(request);
        Order saved = orderRepository.save(order);
        return mapper.toResponse(saved);
    }

    public OrderResponse updateStatus(long id, OrderStatusUpdateRequest request) {
        Objects.requireNonNull(request, "Order status payload is required");
        if (request.status() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order status is required");
        }

        var order = findOrderOrThrow(id);
        mapper.applyStatusUpdate(order, request);
        var saved = orderRepository.save(order);
        return mapper.toResponse(saved);
    }

    public void deleteById(long id) {
        if (!orderRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
        orderRepository.deleteById(id);
    }

    private Order findOrderOrThrow(long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }
}
