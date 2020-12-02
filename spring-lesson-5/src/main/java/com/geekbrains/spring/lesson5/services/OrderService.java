package com.geekbrains.spring.lesson5.services;

import com.geekbrains.spring.lesson5.entities.Order;
import com.geekbrains.spring.lesson5.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

}
