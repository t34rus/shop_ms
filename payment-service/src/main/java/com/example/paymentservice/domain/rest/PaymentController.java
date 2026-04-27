package com.example.paymentservice.domain.rest;

import com.example.paymentservice.domain.model.Payment;
import com.example.paymentservice.domain.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<Payment> findAll() {
        return paymentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> findById(@PathVariable long id) {
        return paymentService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Payment> create(@RequestBody Payment payment) {
        Payment savedPayment = paymentService.create(payment);
        return ResponseEntity.ok(savedPayment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        if (!paymentService.deleteById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
