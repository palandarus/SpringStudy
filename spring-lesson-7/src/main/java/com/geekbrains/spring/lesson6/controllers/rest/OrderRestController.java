package com.geekbrains.spring.lesson6.controllers.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.geekbrains.spring.lesson6.entities.Order;
import com.geekbrains.spring.lesson6.entities.views.OrderView;
import com.geekbrains.spring.lesson6.services.OrderService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders/api/v1")
public class OrderRestController {

    OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<Order> ordersToXml() {
        return orderService.findAll();
    }


    @GetMapping(value = "/id", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(OrderView.Id.class)
    public List<Order> ordersIdToJson() {
        return orderService.findAll();
    }

    @GetMapping(value = "/idCode")
    @JsonView(OrderView.IdCode.class)
    public List<Order> ordersIdCodeToJson() {
        return orderService.findAll();
    }

    @GetMapping(value = "/idCodeCustomer")
    @JsonView(OrderView.IdCodePriceCustomer.class)
    public List<Order> ordersIdCodeCustomerToJson() {
        return orderService.findAll();
    }

    @GetMapping(value = "/idCodeCustomerOrderEntry")
    @JsonView(OrderView.IdCodeCustomerOrderEntry.class)
    public List<Order> ordersIdCodeCustomerOrderEntryToJson() {
        return orderService.findAll();
    }

}
