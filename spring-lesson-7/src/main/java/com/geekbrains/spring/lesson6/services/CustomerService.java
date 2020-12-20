package com.geekbrains.spring.lesson6.services;

import com.geekbrains.spring.lesson6.entities.Customer;
import com.geekbrains.spring.lesson6.repositories.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    public Page<Customer> findAll(int page, int size) {
        return customerRepository.findAll(PageRequest.of(page, size));
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer saveOrUpdate(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer update(Customer customer) {
        customerRepository.save(customer);
        return customerRepository.getOne(customer.getId());
    }


    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public void remove(Long id) {
        customerRepository.deleteById(id);
    }

}
