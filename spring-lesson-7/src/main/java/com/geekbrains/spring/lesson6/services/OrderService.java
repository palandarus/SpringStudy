package com.geekbrains.spring.lesson6.services;

import com.geekbrains.spring.lesson6.entities.Order;
import com.geekbrains.spring.lesson6.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private CartService cartService;

    public OrderService(OrderRepository orderRepository, CartService cartService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Transactional
    public Order createOrder() {
        Order order = new Order();
        order.setCode(UUID.randomUUID().toString().substring(0, 4));
        order.setCustomer(cartService.getCustomer());
        order.setTotalPrice(cartService.getTotalPrice());
        order.setOrderEntries(cartService.getOrderEntries());
        cartService.getOrderEntries().stream().forEach(orderEntry -> {
            orderEntry.setOrder(order);
        });
        orderRepository.save(order);
        cartService.clearCart();
        return order;
    }

    public void remove(Long id) {
        orderRepository.deleteById(id);
    }
}
