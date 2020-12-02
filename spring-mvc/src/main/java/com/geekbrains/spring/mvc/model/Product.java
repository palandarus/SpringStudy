package com.geekbrains.spring.mvc.model;

import java.util.Objects;

public class Product {
    private Long id;
    private Long brandId;
    private String title;
    private String description;
    private Double price;

    public Product() {
    }

    public Product(Long id, Long brandId, String title, String description, Double price) {
        this.id = id;
        this.brandId = brandId;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                "BrandId=" + brandId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}' + "\n";
    }
}
