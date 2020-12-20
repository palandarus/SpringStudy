package com.geekbrains.spring.lesson6.controllers;

import com.geekbrains.spring.lesson6.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String firstRequest(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "orders";
    }

    @GetMapping("/remove/{id}")
    public String remove(
            @PathVariable("id") Long id,
            Model model
    ) {
        orderService.remove(id);
        return "redirect:/orders";
    }

}
