package com.geekbrains.spring.lesson6;


import com.geekbrains.spring.lesson6.utils.DateFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.geekbrains.spring.lesson6.repositories")
@EnableTransactionManagement
@ComponentScan("com.geekbrains.spring.lesson6")
public class AppConfig {

    @Bean
    public DateFormatter dateFormatter() {
        return new DateFormatter();
    }


//    @Bean(name="dataSource")
//    public DataSource getDataSource() {
//        // Создаем источник данных
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        // Задаем параметры подключения к базе данных
//        dataSource.setUrl("jdbc:h2:file:D:\\java-learn\\geekbrains\\spring-lesson-5\\db\\demo;MV_STORE=false");
//        dataSource.setUsername("sa");
//        dataSource.setDriverClassName("org.h2.Driver");
//        dataSource.setPassword("");
//        return dataSource;
//    }
//
//
//    @Bean(name="entityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean getEntityManager() {
//        // Создаем класса фабрики, реализующей интерфейс
//        // FactoryBean<EntityManagerFactory>
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        // Задаем источник подключения
//        factory.setDataSource(getDataSource());
//        // Задаем адаптер для конкретной реализации JPA,
//        // указывает, какая именно библиотека будет использоваться в качестве
//        // поставщика постоянства
//        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        // Указание пакета, в котором будут находиться классы-сущности
//        factory.setPackagesToScan("com.geekbrains.spring.lesson5.entities");
//        // Создание свойств для настройки Hibernate
//        Properties jpaProperties = new Properties();
//        // Указание диалекта конкретной базы данных
//        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//        // Максимальное количество строк, возвращаемых за один запрос из БД
//        jpaProperties.put("hibernate.jdbc.fetch_size", 50);
//        // Максимальное количество запросов при использовании пакетных операций
//        jpaProperties.put("hibernate.jdbc.batch_size", 10);
//        // Включает логирование
//        jpaProperties.put("hibernate.show_sql", true);
//        //jpaProperties.put("hibernate.hbm2ddl.auto", "update");
//        jpaProperties.put("hibernate.hbm2ddl.auto", "create");
//
//        factory.setJpaProperties(jpaProperties);
//        return factory;
//    }
//
//    @Bean(name = "transactionManager")
//    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        // Создание менеджера транзакций
//        JpaTransactionManager tm = new JpaTransactionManager();
//        tm.setEntityManagerFactory(entityManagerFactory);
//        return tm;
//    }
}
