package com.geekbrains.spring.mvc.repositories;

import com.geekbrains.spring.mvc.model.Brand;
import com.geekbrains.spring.mvc.model.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Repository
public class ProductRepository {
    private List<Product> products;
    private List<Brand> brands;

    @PostConstruct
    public void init() {
        this.products = new ArrayList<>();
        this.products.add(new Product(1L, 1L, "Orange", "some description", 1.0));
        this.products.add(new Product(2L, 2L, "Apple", "some description", 1.0));
        this.products.add(new Product(3L, 1L, "Lemon", "some description", 1.0));
        this.products.add(new Product(4L, 3L, "Melon", "some description", 1.0));
        this.products.add(new Product(5L, 3L, "Watermelon", "some description", 1.0));
        this.products.add(new Product(6L, 4L, "Grapefruit", "some description", 1.0));
        this.products.add(new Product(7L, 4L, "Strawberry", "some description", 1.0));
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }

    public Product saveOrUpdate(Product product) {
        if (product.getId() == null) {
            product.setId(products.size() + 1L);
            products.add(product);
            return product;
        } else {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId().equals(product.getId())) {
                    products.set(i, product);
                    return product;
                }
            }
        }
        throw new RuntimeException("Error save or update product");
    }

    public Product findById(Long id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        throw new RuntimeException("Product not found");
    }
}
