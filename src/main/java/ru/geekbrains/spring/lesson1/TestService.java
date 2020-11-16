package ru.geekbrains.spring.lesson1;


import org.springframework.stereotype.Component;

@Component("testService")
public class TestService {

    public String getName(){
        return "Test1";
    }

}
