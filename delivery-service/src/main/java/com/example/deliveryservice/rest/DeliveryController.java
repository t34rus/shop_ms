package com.example.deliveryservice.rest;

import com.example.deliveryservice.rest.dto.DeliveryResponse;
import com.example.deliveryservice.rest.dto.DeliveryCreateRequest;
import com.example.deliveryservice.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryService deliveryService;

    @GetMapping
    public List<DeliveryResponse> deliveries() {
        return deliveryService.findAll();
    }

    @GetMapping("/{id}")
    public DeliveryResponse deliveryById(@PathVariable long id) {
        return deliveryService.findById(id);
    }

    @PostMapping
    public ResponseEntity<DeliveryResponse> createDelivery(@RequestBody DeliveryCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(deliveryService.create(request));
    }
}
