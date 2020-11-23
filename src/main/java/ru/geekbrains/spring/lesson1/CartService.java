package ru.geekbrains.spring.lesson1;

public interface CartService {
    public Double getSum();
    public void addProduct(Product product, Integer quantity);
    public void addProduct(Product product);
    public void delProduct(Product product, Integer quantity);
    public void delProduct(Product product);
    public Cart getCart();
}
