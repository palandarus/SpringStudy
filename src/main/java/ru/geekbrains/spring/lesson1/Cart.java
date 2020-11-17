package ru.geekbrains.spring.lesson1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Scope("prototype")
public class Cart {
    private Map<Product, Integer> products = new HashMap<>();
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Cart() {
    }

    @PostConstruct
    public void init() {
        products.put(productRepository.findById(1L), 1);
        products.put(productRepository.findById(2L), 2);
        products.put(productRepository.findById(3L), 1);
        products.put(productRepository.findById(4L), 3);
        products.put(productRepository.findById(5L), 1);
    }



    public void addProduct(Product product, Integer quantity) {
        if (products.get(product) != null)
            products.put(product, products.get(product) + quantity);
        else products.put(product, quantity);
    }

    @Override
    public String toString() {
        StringBuilder out=new StringBuilder();
        String productName;
        Integer productQuantity=0;
        Double productPrice=0.0;
        Double sum=0.0;
        for (Map.Entry<Product, Integer> entry:products.entrySet()
             ) {
            Product product=entry.getKey();
            double productSumPrice;
            productName=product.getName();
            productQuantity=entry.getValue();
            productPrice=product.getPrice();
            productSumPrice=productPrice*productQuantity;
            out.append("--"+productName+" "+productQuantity+"*"+productPrice+" = "+productSumPrice+"\n");
            sum+=productSumPrice;
        }
        out.append("Sum is ").append(sum);
        return out.toString();
    }

    public Double getSum() {
        Double sum = 0.0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            sum += entry.getKey().getPrice() * entry.getValue();
        }
        return sum;
    }

    public void delProduct(Product product, Integer quantity) {
        if (products.get(product) != null) {
            if (products.get(product) > quantity)
                products.put(product, products.get(product) - quantity);
            else products.remove(product);
        }

    }

    public void delProduct(Product product) {
        products.remove(product);
    }
}
