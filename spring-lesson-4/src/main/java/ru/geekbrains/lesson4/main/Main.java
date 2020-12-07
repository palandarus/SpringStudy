package ru.geekbrains.lesson4.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.geekbrains.lesson4.config.AppConfig;
import ru.geekbrains.lesson4.entity.Article;
import ru.geekbrains.lesson4.entity.User;
import ru.geekbrains.lesson4.repositories.ArticleDataRepository;
import ru.geekbrains.lesson4.repositories.ArticlesPaginationRepository;
import ru.geekbrains.lesson4.repositories.ArticlesRepository;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);


        //ArticleDAO articleDAO = applicationContext.getBean("articleDAO", ArticleDAO.class);
        ArticleDataRepository articleDataRepository = applicationContext.getBean("articleDataRepository", ArticleDataRepository.class);
        ArticlesPaginationRepository articlesPaginationRepository = applicationContext.getBean("articlesPaginationRepository", ArticlesPaginationRepository.class);


        ArticlesRepository articlesRepository = applicationContext.getBean("articlesRepository", ArticlesRepository.class);
        articlesRepository.save(new Article("New Title 1", new User("Alex")));
        articlesRepository.save(new Article("New Title 2", new User("Alena")));
        articlesRepository.save(new Article("New Title 3", new User("Milena")));
        articlesRepository.save(new Article("New Title 4", new User("Lena")));
        articlesRepository.save(new Article("New Title 5", new User("Artem")));
        articlesRepository.save(new Article("New Title 6", new User("Polina")));
        articlesRepository.save(new Article("New Title 7", new User("Vika")));
        articlesRepository.save(new Article("New Title 8", new User("Veronika")));
        articlesRepository.save(new Article("New Title 9", new User("Petr")));
        articlesRepository.save(new Article("New Title 10", new User("Dima")));

//        List<Article> articles = articlesRepository.findByTitle("New Title");
//        System.out.println("articles = " + articles);
//
//        Article article2 = articlesRepository.findById(2l).get();
//        System.out.println("article2 = " + article2);

//        Optional<ArticleData> optional = articleDataRepository.findOneById(8L);
//        System.out.println("id = " + optional.get().getId() + ", " + optional.get().getTitle());

        Pageable pageable = PageRequest.of(0, 2);

        Page<Article> articlePage = articlesPaginationRepository.findAll(pageable);
        System.out.println(articlePage.getContent());


    }

}
