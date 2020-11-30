package ru.geekbrains.lesson4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.lesson4.data.CartData;
import ru.geekbrains.lesson4.entity.Cart;

@Repository
public interface CartDataRepository extends JpaRepository<Cart, Long> {

    @Query("select new ru.geekbrains.lesson4.data.CartData(c) from Cart c JOIN c.user u where c.code = :code")
    CartData findByCode(@Param("code") String code);

}

