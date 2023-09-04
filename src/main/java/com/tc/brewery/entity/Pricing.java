package com.tc.brewery.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Pricing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "beerId", referencedColumnName = "id")
    private Beer beer;

    private int sizeMl;
    private BigDecimal price;
    private String imageUrl;

    public Pricing() {
    }

    public Pricing(int id, Beer beer, int sizeMl, BigDecimal price, String imageUrl) {
        this.id = id;
        this.beer = beer;
        this.sizeMl = sizeMl;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public int getSizeMl() {
        return sizeMl;
    }

    public void setSizeMl(int sizeMl) {
        this.sizeMl = sizeMl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
