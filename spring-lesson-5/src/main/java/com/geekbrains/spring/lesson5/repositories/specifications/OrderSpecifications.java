package com.geekbrains.spring.lesson5.repositories.specifications;

import com.geekbrains.spring.lesson5.entities.Order;
import org.springframework.data.jpa.domain.Specification;

public class OrderSpecifications {

    public static Specification<Order> customerNameLike(String customerNamePart) {


        return (Specification<Order>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("customer").get("name"), String.format("%%%s%%", customerNamePart));
        }; // where customer.name like %customerNamePart%
    }
}
