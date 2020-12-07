package ru.geekbrains.lesson4.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;


    @OneToMany(
            mappedBy = "category",
            fetch = FetchType.EAGER
    )
    List<Product> productList = new ArrayList<>();

    public Category() {
    }

    public Category(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", Title='" + title + '\'' +
                ", Products=" + productList +
                '}';
    }
}
