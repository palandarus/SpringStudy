package com.geekbrains.spring.lesson6.utils;

import com.geekbrains.spring.lesson6.entities.Customer;
import com.geekbrains.spring.lesson6.entities.Order;
import com.geekbrains.spring.lesson6.entities.OrderEntry;
import com.geekbrains.spring.lesson6.entities.Product;
import com.geekbrains.spring.lesson6.repositories.CustomerRepository;
import com.geekbrains.spring.lesson6.repositories.OrderRepository;
import com.geekbrains.spring.lesson6.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class SampleData {

    private CustomerRepository customerRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    public SampleData(CustomerRepository customerRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @PostConstruct
    public void init() {

        Product product1 = new Product("Orange", "Brand Orange", 50.);
        Product product2 = new Product("Lemon", "Brand Lemon", 70.);
        Product product3 = new Product("Lime", "Brand Lime", 20.);
        Product product4 = new Product("Mango", "Brand Mango", 110.);
        Product product5 = new Product("Apple", "Brand Apple", 95.);
        Product product6 = new Product("Pineapple", "Brand Pineapple", 76.);
        Product product7 = new Product("Avocado", "Brand Avocado", 123.);
        Product product8 = new Product("Chicken", "Brand Chicken", 125.);

        Customer customer1 = new Customer("Alex", "test@test1.com", "79000000001", new Date(), "Russia, SPb, Leninskiy street 10-10", "something");
        Customer customer2 = new Customer("Alena", "test@test2.com", "79000000002", new Date(), "Russia, Msk, Leninskiy street 01-01", "something else");

        Order order1 = new Order();
        order1.setTotalPrice(product1.getPrice());
        order1.setCustomer(customer1);

        order1.setCode("0001");

        Order order2 = new Order();
        order2.setTotalPrice(product2.getPrice());
        order2.setCustomer(customer2);

        order2.setCode("0002");

        OrderEntry orderEntry1 = new OrderEntry(product1, order1);
        OrderEntry orderEntry2 = new OrderEntry(product2, order1);
        OrderEntry orderEntry3 = new OrderEntry(product3, order1);
        OrderEntry orderEntry4 = new OrderEntry(product4, order2);
        OrderEntry orderEntry5 = new OrderEntry(product5, order2);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
        productRepository.save(product6);
        productRepository.save(product7);
        productRepository.save(product8);

        customerRepository.save(customer1);
        customerRepository.save(customer2);

        orderRepository.save(order1);
        orderRepository.save(order2);

    }
}
