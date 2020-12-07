package ru.geekbrains.lesson4.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.lesson4.entity.Article;

@Repository
public interface ArticlesPaginationRepository extends PagingAndSortingRepository<Article, Long> {

}
