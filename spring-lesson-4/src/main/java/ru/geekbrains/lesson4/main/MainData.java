package ru.geekbrains.lesson4.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.lesson4.config.AppConfig;
import ru.geekbrains.lesson4.data.CartData;
import ru.geekbrains.lesson4.entity.Cart;
import ru.geekbrains.lesson4.entity.Product;
import ru.geekbrains.lesson4.entity.User;
import ru.geekbrains.lesson4.repositories.CartDataRepository;
import ru.geekbrains.lesson4.services.CartEntryService;
import ru.geekbrains.lesson4.services.CartService;
import ru.geekbrains.lesson4.services.ProductService;
import ru.geekbrains.lesson4.services.UserService;

public class MainData {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        CartService cartService = applicationContext.getBean("cartService", CartService.class);
        CartEntryService cartEntryService = applicationContext.getBean("cartEntryService", CartEntryService.class);
        ProductService productService = applicationContext.getBean("productService", ProductService.class);
        UserService userService = applicationContext.getBean("userService", UserService.class);

        CartDataRepository cartDataRepository = applicationContext.getBean("cartDataRepository", CartDataRepository.class);

        User user1 = new User("Alex");

        userService.save(user1);

        Product product1 = new Product("Apple", 100l, 15.0);
        Product product2 = new Product("Orange", 200l, 30.0);
        Product product3 = new Product("Mango", 30l, 25.0);

        productService.save(product1);
        productService.save(product2);
        productService.save(product3);


        Cart cart1 = new Cart();
        cart1.setCode("0001");
        cart1.setUser(user1);
        cartService.save(cart1);

        cartEntryService.addProduct(cart1, product1, 2);
        cartEntryService.addProduct(cart1, product2, 5);
        cartEntryService.addProduct(cart1, product3, 15);


        CartData cartData = cartDataRepository.findByCode("0001");
        System.out.println("cartData = " + cartData);
    }
}
