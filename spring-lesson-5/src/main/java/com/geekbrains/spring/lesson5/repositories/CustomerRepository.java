package com.geekbrains.spring.lesson5.repositories;

import com.geekbrains.spring.lesson5.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
