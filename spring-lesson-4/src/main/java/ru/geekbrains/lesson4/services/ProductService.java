package ru.geekbrains.lesson4.services;

import ru.geekbrains.lesson4.entity.Category;
import ru.geekbrains.lesson4.entity.Product;

import java.util.List;

public interface ProductService {

    Product get(Long id);

    List<Product> getAll();

    void save(Product product);

    void remove(Product product);

    Product findByName(String name);

    List<Product> findAllByPriceGreaterThan(Double price);

    List<Product> findAllByPriceGreaterThanOrderByPriceDesc(Double price);

    List<Product> findMaxPricedByCategory(Category category);

    List<Product> findMaxPricedProduct();

    List<Product> findMinPricedProduct();

    List<Product> findMinPricedByCategoryID(Category category);

    List<Product> getMinAndMaxPricedProductFromCatalog(Category category);


}
