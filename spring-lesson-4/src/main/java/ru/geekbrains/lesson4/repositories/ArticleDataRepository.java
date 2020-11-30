package ru.geekbrains.lesson4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.lesson4.data.ArticleData;
import ru.geekbrains.lesson4.entity.Article;

import java.util.Optional;

@Repository
public interface ArticleDataRepository extends JpaRepository<Article, Long> {

    Optional<ArticleData> findOneById(Long id);

}

