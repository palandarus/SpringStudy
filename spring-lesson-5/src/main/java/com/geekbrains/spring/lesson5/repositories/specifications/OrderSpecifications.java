package com.geekbrains.spring.lesson5.repositories.specifications;

import com.geekbrains.spring.lesson5.entities.Order;
import org.springframework.data.jpa.domain.Specification;

public class OrderSpecifications {

    public static Specification<Order> customerNameLike(String customerNamePart) {


        return (Specification<Order>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("customer").get("name"), String.format("%%%s%%", customerNamePart));
        }; // where o.customer.name like %customerNamePart%


    }

    public static Specification<Order> currentPriceLessOrEqualsThan(int currentPriceMin) {


        return (Specification<Order>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("currentPrice"), currentPriceMin);
        }; // where o.currentPrice <= currentPriceMin


    }


    public static Specification<Order> currentPriceGreaterOrEqualsThan(int currentPriceMax) {


        return (Specification<Order>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("currentPrice"), currentPriceMax);
        }; // where o.currentPrice >= currentPriceMax


    }


    public static Specification<Order> codeLike(String code) {


        return (Specification<Order>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("code"), String.format("%%%s%%", code));
        }; // where o.code like %code%


    }
}
