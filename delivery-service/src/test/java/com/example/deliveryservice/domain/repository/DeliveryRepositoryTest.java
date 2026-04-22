package com.example.deliveryservice.domain.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.deliveryservice.domain.model.Delivery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeliveryRepositoryTest {

    @Mock
    private DeliveryRepository deliveryRepository;

    @Test
    @SuppressWarnings("null")
    void saveShouldCallRepositoryAndReturnSavedDelivery() {
        Delivery delivery = Delivery.builder()
                .id(1L)
                .orderId(101L)
                .address("Moscow, Tverskaya 1")
                .build();

        when(deliveryRepository.save(delivery)).thenReturn(delivery);

        Delivery savedDelivery = deliveryRepository.save(delivery);

        verify(deliveryRepository).save(delivery);
        assertEquals(101L, savedDelivery.getOrderId());
        assertEquals("Moscow, Tverskaya 1", savedDelivery.getAddress());
    }
}