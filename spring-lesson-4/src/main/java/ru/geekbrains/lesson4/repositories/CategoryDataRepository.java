package ru.geekbrains.lesson4.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.geekbrains.lesson4.entity.Category;

public interface CategoryDataRepository extends PagingAndSortingRepository<Category, Long> {

}
