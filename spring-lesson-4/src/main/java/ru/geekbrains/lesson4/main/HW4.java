package ru.geekbrains.lesson4.main;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.lesson4.config.AppConfig;
import ru.geekbrains.lesson4.entity.Category;
import ru.geekbrains.lesson4.entity.Product;
import ru.geekbrains.lesson4.repositories.CategoryDataRepository;
import ru.geekbrains.lesson4.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Создать сущность «категория» (id, название, список товаров) и соответствующую таблицу в БД. Заполнить таблицу тестовыми данными.
 * 2. Вывести каталоги с товарами в консоль.
 * 3. Написать запросы к репозитариям и вывести товар с:
 * a. минимальной ценой в каталоге,
 * b. максимальной ценой из общего списка,
 * c. минимальной и максимальной цене в каталоге.
 * <p>
 * 4. * Добавить постраничный вывод в консоль товаров из категории
 */

public class HW4 {

    final static int FRUITS_COUNT = 10;
    final static int VEGETABLES_COUNT = 8;


    public static void main(String[] args) {


        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        CategoryDataRepository categoryDataRepository = applicationContext.getBean("categoryDataRepository", CategoryDataRepository.class);
        ProductRepository productRepository = applicationContext.getBean("productRepository", ProductRepository.class);
        initialize(categoryDataRepository, productRepository);


    }

    private static void initialize(CategoryDataRepository categoryDataRepository, ProductRepository productRepository) {

        categoryDataRepository.deleteAll();
        productRepository.deleteAll();


        Category fruitCategory = new Category("Fruits");
        Category vegetableCategory = new Category("Vegetables");
        List<Product> fruitList = new ArrayList<>();
        List<Product> vegetableList = new ArrayList<>();

        for (int i = 1; i <= FRUITS_COUNT; i++) {
            fruitList.add(new Product("Fruit " + i, 100.0 / i, fruitCategory));
        }

        for (int i = 1; i <= VEGETABLES_COUNT; i++) {
            fruitList.add(new Product("Vegetable " + i, 100.0 / i, vegetableCategory));
        }

        productRepository.saveAll(fruitList);
        productRepository.saveAll(vegetableList);
        categoryDataRepository.save(fruitCategory);
        categoryDataRepository.save(vegetableCategory);
    }

}
