package ru.geekbrains.spring.lesson1;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> productList=new ArrayList<>();

    public List<Product> findAll(){
        return Collections.unmodifiableList(productList);
    }

    public void addProduct(Product product){
        productList.add(product);
    }

    public ProductRepository() {

    }

    @PostConstruct
    public void init(){
        productList.add(new Product(1l,"Apple", 50.0));
        productList.add(new Product(2l,"Lemon", 75.0));
        productList.add(new Product(3l,"Orange", 100.0));
        productList.add(new Product(4l,"Watermelon", 20.0));
        productList.add(new Product(5l,"Kiwi", 150.0));
    }

}
