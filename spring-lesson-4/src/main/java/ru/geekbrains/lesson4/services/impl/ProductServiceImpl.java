package ru.geekbrains.lesson4.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.lesson4.entity.Category;
import ru.geekbrains.lesson4.entity.Product;
import ru.geekbrains.lesson4.repositories.ProductRepository;
import ru.geekbrains.lesson4.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private Collectors IterableUtils;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Product get(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAll() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void remove(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> findAllByPriceGreaterThan(Double price) {
        return productRepository.findAllByPriceGreaterThanEqual(price);
    }


    @Override
    public List<Product> findAllByPriceGreaterThanOrderByPriceDesc(Double price) {
        return productRepository.findAllByPriceGreaterThanOrderByPriceDesc(price);
    }

    @Override
    public List<Product> findMinPricedByCategoryID(Category category) {
        return productRepository.findMinPricedByCategory(category);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findMaxPricedByCategory(Category category) {
        return productRepository.findMaxPricedByCategory(category);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findMaxPricedProduct() {
        return productRepository.findMaxPricedProduct();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findMinPricedProduct() {
        return productRepository.findMinPricedProduct();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getMinAndMaxPricedProductFromCatalog(Category category) {
        return productRepository.getMinAndMaxPricedProductFromCategory(category);
    }
}
