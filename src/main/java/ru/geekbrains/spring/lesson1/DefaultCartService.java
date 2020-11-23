package ru.geekbrains.spring.lesson1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultCartService implements CartService {


    private Cart cart;

    @Override
    public Double getSum() {
        return cart.getSum();
    }

    @Override
    public void addProduct(Product product, Integer quantity) {
        cart.addProduct(product, quantity);
    }

    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    @Override
    public void delProduct(Product product, Integer quantity) {
        cart.delProduct(product,quantity);
    }

    @Override
    public void delProduct(Product product) {
        cart.delProduct(product);
    }

    @Override
    public Cart getCart() {
        return cart;
    }
}
