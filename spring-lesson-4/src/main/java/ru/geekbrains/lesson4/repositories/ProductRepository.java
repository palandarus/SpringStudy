package ru.geekbrains.lesson4.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.lesson4.entity.Category;
import ru.geekbrains.lesson4.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByPriceGreaterThanEqual(Double price);

    List<Product> findAllByPriceGreaterThanOrderByPriceDesc(Double price);

    Product findByName(String name);

    @Query("select p from Product p where p.price=(select max(price) from Product where category = :category)")
    List<Product> findMaxPricedByCategory(@Param("category") Category category);

    @Query("select p from Product p where p.price=(select max(price) from Product)")
    List<Product> findMaxPricedProduct();

    @Query("select p from Product p where p.price=(select min(price) from Product)")
    List<Product> findMinPricedProduct();


    @Query("select p from Product p where p.price=(select min(price) from Product where category = :category)")
    List<Product> findMinPricedByCategory(@Param("category") Category category);


//    @Query("select p from Product p where ((p.price=(select min(p.price) from Product p where p.category = :category)) or (p.price=(select max(p.price) from Product p where p.category = :category))) and  p.category = :category")
//    List<Product> getMinAndMaxPricedProductFromCategory(@Param("category") Category category);

    @Query("select p from Product p where ((p.price=(select min(price) from Product)) or (p.price=(select max(price) from Product ))) and  p.category = :category")
    List<Product> getMinAndMaxPricedProductFromCategory(@Param("category") Category category);

    Page<Product> findAllByCategory(Category category, Pageable pageable);

}
