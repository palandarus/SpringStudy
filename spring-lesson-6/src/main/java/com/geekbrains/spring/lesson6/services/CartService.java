package com.geekbrains.spring.lesson6.services;


import com.geekbrains.spring.lesson6.entities.Customer;
import com.geekbrains.spring.lesson6.entities.OrderEntry;
import com.geekbrains.spring.lesson6.entities.Product;
import com.geekbrains.spring.lesson6.repositories.CustomerRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartService { //cartService

    private CustomerRepository customerRepository;

    private List<OrderEntry> orderEntries;
    private Double totalPrice = 0.0;
    private Integer totalQuantity = 0;
    private Customer customer;

    public CartService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostConstruct
    public void init() {
        orderEntries = new ArrayList<>();
        customer = customerRepository.getOne(1l);
    }

    public void addOneAndUpdate(Product p) {
        for (OrderEntry orderEntry : orderEntries) {
            if (orderEntry.getProduct().getId().equals(p.getId())) {
                orderEntry.setQuantity(orderEntry.getQuantity() + 1);
                recalculate();
                return;
            }
        }
        orderEntries.add(new OrderEntry(p));
        recalculate();
        //System.out.println("total = " + totalPrice);
        //System.out.println("size entry = " + orderEntries.size());
    }

    public void removeOneAndUpdate(Long productId) {
        Iterator<OrderEntry> iterator = getOrderEntryIterator();
        while (iterator.hasNext()) {
            OrderEntry orderEntry = iterator.next();
            if (orderEntry.getProduct().getId().equals(productId)) {
                if (orderEntry.getQuantity() - 1 == 0) {
                    iterator.remove();
                } else {
                    orderEntry.setQuantity(orderEntry.getQuantity() - 1);
                }
                recalculate();
                return;
            }
        }
    }

    public void removeAll(Long productId) {
        Iterator<OrderEntry> iterator = getOrderEntryIterator();
        while (iterator.hasNext()) {
            OrderEntry o = iterator.next();
            if (o.getProduct().getId().equals(productId)) {
                iterator.remove();
                recalculate();
                return;
            }
        }
    }

    public void recalculate() {
        totalPrice = 0.0;
        totalQuantity = 0;
        for (OrderEntry orderEntry : orderEntries) {
            Double price = orderEntry.getBasePrice() * orderEntry.getQuantity();
            orderEntry.setTotalPrice(price);
            totalPrice += price;
            totalQuantity += orderEntry.getQuantity();
        }
    }

    public void clearCart() {
        orderEntries = new ArrayList<>();
        recalculate();
    }

    Iterator<OrderEntry> getOrderEntryIterator() {
        return orderEntries.iterator();
    }

    public List<OrderEntry> getOrderEntries() {
        return orderEntries;
    }

    public void setOrderEntries(List<OrderEntry> orderEntries) {
        this.orderEntries = orderEntries;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
