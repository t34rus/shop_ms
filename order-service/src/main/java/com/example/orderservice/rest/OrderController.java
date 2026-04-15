package com.example.orderservice.rest;

import com.example.orderservice.domain.model.Order;
import com.example.orderservice.domain.repository.OrderRepository;
import com.example.orderservice.rest.dto.OrderCreateRequest;
import com.example.orderservice.rest.dto.OrderMapper;
import com.example.orderservice.rest.dto.OrderResponse;
import com.example.orderservice.rest.dto.OrderStatusUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;

    @GetMapping
    public List<OrderResponse> orders() {
        return orderRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    @GetMapping("/{id}")
    public OrderResponse orderById(@PathVariable long id) {
        return mapper.toResponse(findOrderOrThrow(id));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderCreateRequest request) {
        var order = mapper.toNewEntity(request);
        Order saved = orderRepository.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(saved));
    }

    @PatchMapping("/{id}/status")
    public OrderResponse updateStatus(@PathVariable long id, @RequestBody OrderStatusUpdateRequest request) {
        Objects.requireNonNull(request, "Order status payload is required");
        if (request.status() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order status is required");
        }

        var order = findOrderOrThrow(id);
        mapper.applyStatusUpdate(order, request);
        var saved = orderRepository.save(order);
        return mapper.toResponse(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable long id) {
        if (!orderRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
        orderRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private Order findOrderOrThrow(long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }
}
