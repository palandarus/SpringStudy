package com.geekbrains.spring.lesson5.services;

import com.geekbrains.spring.lesson5.entities.Order;
import com.geekbrains.spring.lesson5.repositories.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Page<Order> findAll(Specification<Order> spec, int page, int size) {
        return orderRepository.findAll(spec, PageRequest.of(page, size));
    }

}
