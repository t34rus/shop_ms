package com.example.order_service.domain.repository;

import com.example.order_service.TestOrderServiceApplication;
import com.example.order_service.domain.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
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