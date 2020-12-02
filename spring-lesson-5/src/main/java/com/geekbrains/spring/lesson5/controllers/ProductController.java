package com.geekbrains.spring.lesson5.controllers;

import com.geekbrains.spring.lesson5.entities.Product;
import com.geekbrains.spring.lesson5.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.lesson5.services.ProductService;
import com.geekbrains.spring.lesson5.utils.ProductFilter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //http://localhost:8989/store/products?p=2&min_price=1

    @GetMapping
    public String showAllProducts(Model model,
                                  @RequestParam(defaultValue = "1", name = "p") Integer page,
                                  @RequestParam Map<String, String> params
    ) {
        if (page < 1) {
            page = 1;
        }
        ProductFilter productFilter = new ProductFilter(params);
        Page<Product> products = productService.findAll(productFilter.getSpec(), page - 1, 5);
        model.addAttribute("products", products);
//        products.get().forEach(System.out::println);
//        System.out.println("products count = " + products.getSize());
        model.addAttribute("filterDefinition", productFilter.getFilterDefinition());
        return "products";
    }

    @GetMapping("/add")
    public String addProduct(
            Model model
    ) {
        model.addAttribute("product", new Product());
        return "product_add_form";
    }

    @PostMapping("/add")
    public String addProduct(
            @Valid @ModelAttribute Product product,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "product_add_form";
        }
        productService.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Product getOneProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exists"));
    }

    @GetMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String deleteOneProductById(@PathVariable Long id) {
        productService.deleteById(id);
        return "ok";
    }
}
