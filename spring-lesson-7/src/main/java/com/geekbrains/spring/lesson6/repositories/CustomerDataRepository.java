package com.geekbrains.spring.lesson6.repositories;

import com.geekbrains.spring.lesson6.data.CustomerData;
import com.geekbrains.spring.lesson6.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerDataRepository extends JpaRepository<Customer, Long> {
    @Query("select new com.geekbrains.spring.lesson6.data.CustomerData(c.id, c.name, c.email, c.phone, c.birthday, c.address, c.description, c.createDate, c.modifyDate) from Customer c")
    List<CustomerData> findAllCustomerData();
}
