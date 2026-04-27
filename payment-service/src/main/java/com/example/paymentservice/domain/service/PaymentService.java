package com.example.paymentservice.domain.service;

import com.example.paymentservice.domain.model.Payment;
import com.example.paymentservice.domain.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> findById(long id) {
        return paymentRepository.findById(id);
    }

    public Payment create(Payment payment) {
        return paymentRepository.save(Objects.requireNonNull(payment, "payment must not be null"));
    }

    public boolean deleteById(long id) {
        if (!paymentRepository.existsById(id)) {
            return false;
        }

        paymentRepository.deleteById(id);
        return true;
    }
}
