package com.geekbrains.spring.lesson6.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.geekbrains.spring.lesson6.entities.views.OrderView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends AbstractItem {

    @Column(name = "code")
    @JsonView(OrderView.IdCode.class)
    private String code;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonView(OrderView.IdCodePriceCustomer.class)
    private Customer customer;

    @Column(name = "totalPrice")
    @JsonView(OrderView.IdCodePrice.class)
    private Double totalPrice;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JsonView(OrderView.IdCodeCustomerOrderEntry.class)
    private List<OrderEntry> orderEntries = new ArrayList<>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderEntry> getOrderEntries() {
        return orderEntries;
    }

    public void setOrderEntries(List<OrderEntry> orderEntries) {
        this.orderEntries = orderEntries;
    }
}
