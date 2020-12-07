package ru.geekbrains.lesson4.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.lesson4.entity.Article;

@Repository
public interface ArticlesCrudRepository extends CrudRepository<Article, Long> {

}

