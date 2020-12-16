package com.geekbrains.spring.lesson8.controllers;

import com.geekbrains.spring.lesson8.entities.Product;
import com.geekbrains.spring.lesson8.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.lesson8.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String getProductById(
            @PathVariable Long id,
            Model model
    ) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exists"));
        model.addAttribute("product", product);
        return "product";
    }


}
