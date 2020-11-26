package ru.geekbrains.lesson3.hibernate;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.lesson3.hibernate.entity.Buyer;
import ru.geekbrains.lesson3.hibernate.entity.Category;
import ru.geekbrains.lesson3.hibernate.entity.Goods;
import ru.geekbrains.lesson3.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class HW3 {

    /**
     * 1. В базе данных необходимо реализовать возможность хранить информацию о покупателях (id, имя)
     * и товарах (id, название, стоимость). У каждого покупателя свой набор купленных товаров.
     * Задача: написать тестовое приложение, которое позволит посмотреть, какие товары покупал клиент,
     * какие клиенты купили определенный товар, и предоставит возможность удалять из базы товары/покупателей.
     * <p>
     * 2. * Дополнителное: добавить детализацию по паре «покупатель — товар»: сколько стоил товар в момент
     * покупки клиентом.
     */


    public static void main(String[] args) {

        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
        EntityManager em = factory.createEntityManager();

        final int BUYERSCOUNT = 2;
        final int GOODSCOUNT = 2;
        final String BUYERNAMESUFFIX = "Mr. ";
        final String GOODSNAMESUFFIX = "Goods № ";

        clear(em);
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            List<Buyer> buyerList = new ArrayList<>();
            List<Goods> goodsList = new ArrayList<>();

            for (int i = 1; i <= BUYERSCOUNT; i++) {
                buyerList.add(new Buyer(BUYERNAMESUFFIX + i));
            }

            for (int i = 1; i <= GOODSCOUNT; i++) {
                goodsList.add(new Goods(GOODSNAMESUFFIX + i, Double.valueOf(i * 100 / i)));
            }


            transaction.commit();
        } finally {
            if (em != null)
                em.close();
        }

    }

    private static void clear(EntityManager em) {

    }
}
