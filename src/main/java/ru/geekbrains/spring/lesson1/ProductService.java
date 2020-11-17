package ru.geekbrains.spring.lesson1;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

public List<Product> getProductList(){
        return productRepository.findAll();
}

}
