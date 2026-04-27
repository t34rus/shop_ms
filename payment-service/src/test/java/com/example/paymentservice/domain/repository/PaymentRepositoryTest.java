package com.example.paymentservice.domain.repository;

import com.example.paymentservice.domain.model.Payment;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PaymentRepositoryTest {

    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    void shouldSavePaymentAndFindItById() {
        Payment payment = Payment.builder()
                .id(1L)
                .orderId(101L)
                .build();

        paymentRepository.save(Objects.requireNonNull(payment));

        Optional<Payment> savedPayment = paymentRepository.findById(1L);
        assertThat(savedPayment).isPresent();
        assertThat(savedPayment.get().getOrderId()).isEqualTo(101L);
    }

    @Test
    void shouldReturnAllSavedPayments() {
        Payment firstPayment = Payment.builder()
                .id(1L)
                .orderId(101L)
                .build();
        Payment secondPayment = Payment.builder()
                .id(2L)
                .orderId(202L)
                .build();

        paymentRepository.saveAll(Objects.requireNonNull(List.of(firstPayment, secondPayment)));

        List<Payment> payments = paymentRepository.findAll();
        assertThat(payments)
                .hasSize(2)
                .extracting(Payment::getId)
                .containsExactlyInAnyOrder(1L, 2L);
    }
}