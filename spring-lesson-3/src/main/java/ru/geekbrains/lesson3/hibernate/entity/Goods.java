package ru.geekbrains.lesson3.hibernate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "orders",
            joinColumns = @JoinColumn(name = "goods_id"),
            inverseJoinColumns = @JoinColumn(name = "buyer_id")
    )
    private List<Buyer> buyers = new ArrayList<>();

    public Goods(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Goods() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Buyer> getBuyers() {
        return buyers;
    }

    public void setBuyers(List<Buyer> buyers) {
        this.buyers = buyers;
    }


    public void addBuyersToList(Buyer buyer) {
        if (!buyers.contains(buyer))
            this.buyers.add(buyer);
    }

    public String showBuyerList() {
        StringBuilder out = new StringBuilder();
        for (Buyer buyer : buyers
        ) {
            out.append("\n").append(buyer);
        }
        return out.toString();
    }

    @Override
    public String toString() {
        return String.format("Goods [id = %d, name = %s, price = %f]", id, name, price);
    }
}
