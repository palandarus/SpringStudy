package ru.geekbrains.lesson4.main;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    final static int FRUITS_COUNT = 210;
    final static int VEGETABLES_COUNT = 180;


    public static void main(String[] args) {


        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        CategoryDataRepository categoryDataRepository = applicationContext.getBean("categoryDataRepository", CategoryDataRepository.class);
        ProductRepository productRepository = applicationContext.getBean("productRepository", ProductRepository.class);
        initialize(categoryDataRepository, productRepository);


        categoryDataRepository.findAll().forEach(category -> {
            System.out.println(category);
        });
        Category category = categoryDataRepository.findById(1L).get();
        System.out.println("Написать запросы к репозитариям и вывести товар с:\n" +
                " * a. минимальной ценой в каталоге " + category.getTitle());
        System.out.println(productRepository.findMinPricedByCategory(category));


        System.out.println("Написать запросы к репозитариям и вывести товар с:\n" +
                " * максимальной ценой из общего списка");
        System.out.println(productRepository.findMaxPricedProduct());

        category = categoryDataRepository.findById(2L).get();
        System.out.println("Написать запросы к репозитариям и вывести товар с:\n" +
                " * минимальной и максимальной цене в каталоге " + category.getTitle());
        System.out.println(productRepository.getMinAndMaxPricedProductFromCategory(category));  //почему то попадает фрукты [Product{id=1, name='Fruit 1', price=100.0, category=Fruits}, Product{id=12, name='Vegetable 1', price=100.0, category=Vegetables}, Product{id=21, name='Vegetable 10', price=10.0, category=Vegetables}]

        System.out.println("Вывод товаров из категории " + category);
        Pageable pageable = PageRequest.of(0, 10);
//        productRepository.findByCategory()

    }

    private static void initialize(CategoryDataRepository categoryDataRepository, ProductRepository productRepository) {

        Category fruitCategory = new Category("Fruits");
        Category vegetableCategory = new Category("Vegetables");
        List<Product> fruitList = new ArrayList<>();
        List<Product> vegetableList = new ArrayList<>();
        categoryDataRepository.save(fruitCategory);
        categoryDataRepository.save(vegetableCategory);

        for (int i = 1; i <= FRUITS_COUNT; i++) {
            fruitList.add(new Product("Fruit " + i, FRUITS_COUNT * 1.0 / i, fruitCategory));
        }

        for (int i = 1; i <= VEGETABLES_COUNT; i++) {
            vegetableList.add(new Product("Vegetable " + i, VEGETABLES_COUNT * 1.0 / i, vegetableCategory));
        }
        productRepository.saveAll(fruitList);
        productRepository.saveAll(vegetableList);
    }

}
