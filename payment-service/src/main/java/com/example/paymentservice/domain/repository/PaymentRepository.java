package com.example.paymentservice.domain.repository;

import com.example.paymentservice.domain.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
