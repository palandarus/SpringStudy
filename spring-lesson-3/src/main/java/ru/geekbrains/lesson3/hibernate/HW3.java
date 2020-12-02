package ru.geekbrains.lesson3.hibernate;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.lesson3.hibernate.entity.Buyer;
import ru.geekbrains.lesson3.hibernate.entity.Goods;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
                .addAnnotatedClass(Buyer.class)
                .addAnnotatedClass(Goods.class)
                .buildSessionFactory();
        EntityManager em = factory.createEntityManager();

        final int BUYERSCOUNT = 15;
        final int GOODSCOUNT = 100;
        final String BUYERNAMESUFFIX = "Mr. ";
        final String GOODSNAMESUFFIX = "Goods № ";

        clear(em);
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            List<Buyer> buyerList = new ArrayList<>();
            List<Goods> goodsList = new ArrayList<>();
            List<List<Goods>> buyerGoodsList = new ArrayList<>();

            fillGoodsList(GOODSCOUNT, GOODSNAMESUFFIX, goodsList);
            fiilBuyerList(BUYERSCOUNT, BUYERNAMESUFFIX, buyerList);

            for (Buyer buyer : buyerList
            ) {
                generateBuyerOrder(GOODSCOUNT, goodsList, buyer);
                em.persist(buyer);
            }

            for (Goods goods :
                    goodsList) {

                em.persist(goods);
            }

            transaction.commit();

//            for (Buyer buyer : buyerList
//            ) {
//                System.out.println(buyer);
//                System.out.println(buyer.showBuyerOrder());
//                System.out.println("=====================================================");
//            }

            transaction.begin();
            Long buyerMaxId = em.createQuery("SELECT MAX(b.id) FROM Buyer b", Long.class).getSingleResult();
            Long goodsMaxId = em.createQuery("SELECT MAX(g.id) FROM Goods g", Long.class).getSingleResult();
            Long buyerMinId = buyerMaxId - BUYERSCOUNT + 1;
            Long goodsMinId = goodsMaxId - GOODSCOUNT + 1;
            System.out.printf("bMaxID=%d, bMinID=%d, gMaxID=%d, gMinID=%d", buyerMaxId, buyerMinId, goodsMaxId, goodsMinId);
            Long buyerForTestID = ThreadLocalRandom.current().nextLong(buyerMinId, buyerMaxId);
            Long goodsForTestID = ThreadLocalRandom.current().nextLong(goodsMinId, goodsMaxId);
            Buyer buyer = em.find(Buyer.class, buyerForTestID);
            Goods goods = em.find(Goods.class, goodsForTestID);

            System.out.println("Client " + buyerForTestID + " " + buyer + " bought:");
            System.out.println(buyer.showBuyerOrder());
            System.out.println("Goods " + goodsForTestID + " " + goods + " was bought by:");
            System.out.println(goods.showBuyerList());
            System.out.println("DELETING buyer " + buyer);
            System.out.println("DELETING goods " + goods);
            em.remove(buyer);
            em.remove(goods);
            transaction.commit();

            transaction.begin();
            buyer = em.find(Buyer.class, buyerForTestID);
            goods = em.find(Goods.class, goodsForTestID);
            System.out.println(buyer);
            System.out.println(goods);
            transaction.commit();

        } finally {
            factory.close();
            if (em != null) {
                em.close();
            }
        }

    }

    private static void generateBuyerOrder(int GOODSCOUNT, List<Goods> goodsList, Buyer buyer) {
        Random rd = new Random();
        for (int i = 0; i < GOODSCOUNT; i++) {
            if (rd.nextBoolean()) {

                Goods tempGoods = goodsList.get(i);
                buyer.addGoodsToList(tempGoods);
                tempGoods.addBuyersToList(buyer);

            }
        }
    }

    private static void fiilBuyerList(int BUYERSCOUNT, String BUYERNAMESUFFIX, List<Buyer> buyerList) {
        for (int i = 1; i <= BUYERSCOUNT; i++) {
            Buyer tempBuyer = new Buyer(BUYERNAMESUFFIX + i);
            buyerList.add(tempBuyer);
        }
    }

    private static void fillGoodsList(int GOODSCOUNT, String GOODSNAMESUFFIX, List<Goods> goodsList) {
        for (int i = 1; i <= GOODSCOUNT; i++) {
            double price = 100.0 / i;
            Goods tempGoods = new Goods(GOODSNAMESUFFIX + i, Double.valueOf(price));
            goodsList.add(tempGoods);
        }
    }

    private static void clear(EntityManager em) {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Buyer ").executeUpdate();
        em.createQuery("DELETE FROM Goods ").executeUpdate();
        em.getTransaction().commit();
    }
}
