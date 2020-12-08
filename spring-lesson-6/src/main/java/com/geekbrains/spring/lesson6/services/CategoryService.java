package com.geekbrains.spring.lesson6.services;

import com.geekbrains.spring.lesson6.entities.Category;
import com.geekbrains.spring.lesson6.repositories.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    public Page<Category> findAll(int page, int size) {
        return categoryRepository.findAll(PageRequest.of(page, size));
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void saveOrUpdate(Category category) {
        categoryRepository.save(category);
    }

}
