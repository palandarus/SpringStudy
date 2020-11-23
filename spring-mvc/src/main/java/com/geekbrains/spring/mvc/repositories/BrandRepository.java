package com.geekbrains.spring.mvc.repositories;

import com.geekbrains.spring.mvc.model.Brand;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Repository
public class BrandRepository {
    private List<Brand> brands;

    @PostConstruct
    public void init() {
        this.brands = new ArrayList<>();
        this.brands.add(new Brand(1L, "Citrus unlimited", "some brand description"));
        this.brands.add(new Brand(1L, "Apples 4 all", "some brand description"));
        this.brands.add(new Brand(1L, "MellonMind", "some brand description"));
        this.brands.add(new Brand(1L, "Crimea Inc", "some brand description"));
    }

    public List<Brand> findAll() {
        return Collections.unmodifiableList(brands);
    }

    public Brand saveOrUpdate(Brand brand) {
        if (brand.getId() == null) {
            brand.setId(brands.size() + 1L);
            brands.add(brand);
            return brand;
        } else {
            for (int i = 0; i < brands.size(); i++) {
                if (brands.get(i).getId().equals(brand.getId())) {
                    brands.set(i, brand);
                    return brand;
                }
            }
        }
        throw new RuntimeException("Error save or update brand");
    }

    public Brand findById(Long id) {
        for (Brand brand : brands) {
            if (brand.getId().equals(id)) {
                return brand;
            }
        }
        throw new RuntimeException("Brand not found");
    }
}
