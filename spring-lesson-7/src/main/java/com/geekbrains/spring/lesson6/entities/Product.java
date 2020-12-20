package com.geekbrains.spring.lesson6.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.geekbrains.spring.lesson6.entities.views.ProductView;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product extends AbstractItem {

    @NotEmpty
    @Size(min = 3, max = 20, message = "Title must have 3-20 characters")
    @Column(name = "title")
    @JsonView(ProductView.IdName.class)
    private String title;

    @NotEmpty
    @Size(min = 3, max = 20, message = "Title must have 3-20 characters")
    @Column(name = "brand_name")
    @JsonView(ProductView.IdName.class)
    private String brandName;

    @Column(name = "price")
    @JsonView(ProductView.FullProduct.class)
    private Double price;

    public Product() {
    }

    public Product(String title, String brandName, Double price) {
        this.title = title;
        this.brandName = brandName;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + getId() +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
