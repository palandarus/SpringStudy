package ru.geekbrains.lesson3.hibernate.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "buyers")
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "orders",
            joinColumns = @JoinColumn(name = "buyer_id"),
            inverseJoinColumns = @JoinColumn(name = "goods_id")
    )
    private List<Goods> readers = new ArrayList<>();

    public Buyer() {
    }

    public Buyer(String name) {
        this.name = name;
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

    public List<Goods> getReaders() {
        return readers;
    }

    public void setReaders(List<Goods> readers) {
        this.readers = readers;
    }

    @Override
    public String toString() {
        return String.format("Buyer [id = %d, name = %s]", id, name);
    }
}
