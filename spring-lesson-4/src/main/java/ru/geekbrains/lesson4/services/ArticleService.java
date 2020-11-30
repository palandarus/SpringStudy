package ru.geekbrains.lesson4.services;

import ru.geekbrains.lesson4.entity.Article;

import java.util.List;

public interface ArticleService {

    List<Article> getAll();

    Article get(Long id);

    void save(Article article);

}
