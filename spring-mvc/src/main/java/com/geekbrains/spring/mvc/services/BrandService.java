package com.geekbrains.spring.mvc.services;

import com.geekbrains.spring.mvc.model.Brand;
import com.geekbrains.spring.mvc.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    private BrandRepository brandRepository;

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Brand saveOrUpdate(Brand brand) {
        return brandRepository.saveOrUpdate(brand);
    }

    public Brand findById(Long id) {
        return brandRepository.findById(id);
    }

    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }
}
