package com.geekbrains.spring.lesson6.controllers;


import com.geekbrains.spring.lesson6.entities.Category;
import com.geekbrains.spring.lesson6.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.lesson6.services.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String showAllCategories(Model model,
                                    @RequestParam(defaultValue = "1", name = "p") Integer page
    ) {
        page = (page < 1) ? 1 : page;
        Page<Category> categories = categoryService.findAll(page - 1, 5);
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/add")
    public String addCategory(
            Model model
    ) {
        model.addAttribute("category", new Category());
        return "category_add_form";
    }

    @PostMapping("/add")
    public String addCategory(
            @Valid @ModelAttribute Category category,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "category_add_form";
        }
        categoryService.addCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Category getOneCategoryById(@PathVariable Long id) {
        return categoryService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category with id: " + id + " doesn't exists"));
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Category c = categoryService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category with id: " + id + " doesn't exists (for edit)"));
        model.addAttribute("category", c);
        return "category_edit_form";
    }

    @PostMapping("/edit")
    public String showEditForm(@ModelAttribute Category category) {
        categoryService.saveOrUpdate(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String deleteOneProductById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return "ok";
    }
}
