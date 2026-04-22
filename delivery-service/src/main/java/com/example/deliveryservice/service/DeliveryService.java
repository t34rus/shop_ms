package com.example.deliveryservice.service;

import com.example.deliveryservice.domain.model.Delivery;
import com.example.deliveryservice.domain.repository.DeliveryRepository;
import com.example.deliveryservice.rest.dto.DeliveryCreateRequest;
import com.example.deliveryservice.rest.dto.DeliveryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    public List<DeliveryResponse> findAll() {
        return deliveryRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public DeliveryResponse findById(long id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Delivery not found"));
        return toResponse(delivery);
    }

    public DeliveryResponse create(DeliveryCreateRequest request) {
        Objects.requireNonNull(request, "Delivery payload is required");
        Delivery delivery = Delivery.builder()
                .id(request.id())
                .orderId(request.orderId())
                .address(request.address())
                .build();

        Delivery savedDelivery = deliveryRepository.save(Objects.requireNonNull(delivery));
        return toResponse(savedDelivery);
    }

    private DeliveryResponse toResponse(Delivery delivery) {
        return new DeliveryResponse(
                delivery.getId(),
                delivery.getOrderId(),
                delivery.getAddress()
        );
    }
}
