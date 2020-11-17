package ru.geekbrains.spring.lesson1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultProductService implements ProductService {

    //    @Autowired
    private ProductRepository productRepository;

    /*public ProductService(ProductRepository productRepository) {

        this.productRepository = productRepository;

    }*/

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {

        this.productRepository = productRepository;

    }

    @Override
    public List<Product> getProductList() {

        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {

        return productRepository.findById(id);

    }
}
