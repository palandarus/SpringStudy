package ru.geekbrains.spring.lesson1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {


    public static void main(String[] args) {

        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
//        TestService testService=context.getBean("testService",TestService.class);
//        System.out.println(testService.getName());
//
//        ClassPathXmlApplicationContext xmlContext=new ClassPathXmlApplicationContext("spring-context.xml");
//        TestXmlService testXmlService=xmlContext.getBean("TestXmlService", TestXmlService.class);
//        System.out.println(testXmlService.getName());
//
//        TestService2 testService2=context.getBean("testService2",TestService2.class);
//        System.out.println(testService2.getValue());
        ProductService productService =context.getBean("defaultProductService", ProductService.class);
        System.out.println(productService.getProductList());
        System.out.println("Product with ID = 4 "+productService.getProductById(4l));
        printMiddleDelimeter();
        CartService cartService=context.getBean("defaultCartService", DefaultCartService.class);
        System.out.println("The Cart is: ");
        System.out.println(cartService.getCart());
        System.out.println("Cart Sum is " + cartService.getSum());
        printMiddleDelimeter();
        System.out.println("Adding products in cart");
        cartService.addProduct(productService.getProductById(4l),3);
        System.out.println("Cart Sum is " + cartService.getSum());
        printMiddleDelimeter();
        System.out.println("Delete one product with ID=4 in cart");
        cartService.delProduct(productService.getProductById(4l),1);
        System.out.println(cartService.getCart());
        System.out.println("Cart Sum is " + cartService.getSum());
        printMiddleDelimeter();
        System.out.println("Delete all products with ID=4 in cart");
        cartService.delProduct(productService.getProductById(4l));
        System.out.println("Cart Sum is " + cartService.getSum());
        System.out.println(cartService.getCart());
    }

    public static void printMiddleDelimeter(){
        System.out.println("\n==========================================================================\n");
    }
}
