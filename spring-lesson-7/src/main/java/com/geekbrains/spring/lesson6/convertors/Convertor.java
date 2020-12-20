package com.geekbrains.spring.lesson6.convertors;

public interface Convertor<TARGET, SOURCE> {
    TARGET convert(SOURCE source);
}
