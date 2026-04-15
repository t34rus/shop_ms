package com.example.orderservice.domain.repository;

import com.example.orderservice.TestOrderServiceApplication;
import com.example.orderservice.domain.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ContextConfiguration(classes = TestOrderServiceApplication.class)
class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;


    @Test
    void repositoryBeanIsLoaded() {
        assertNotNull(orderRepository);
    }

    @Test
    void loadAll(){
        List<Order> all = orderRepository.findAll();
        assertEquals(0, all.size());
    }



}