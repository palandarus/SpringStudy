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


    }
}
