package ru.geekbrains.spring.lesson1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {


    public static void main(String[] args) {

        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
        TestService testService=context.getBean("testService",TestService.class);
        System.out.println(testService.getName());

        ClassPathXmlApplicationContext xmlContext=new ClassPathXmlApplicationContext("spring-context.xml");
        TestXmlService testXmlService=xmlContext.getBean("TestXmlService", TestXmlService.class);
        System.out.println(testXmlService.getName());

        TestService2 testService2=context.getBean("testService2",TestService2.class);
        System.out.println(testService2.getValue());


    }
}
