package ru.geekbrains.spring.lesson1;

import java.util.List;

public interface ProductService {

    List<Product> getProductList();
    Product getProductById(Long id);

}
