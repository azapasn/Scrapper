package com.example.demo.model;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Advertisement {
    @Id
    @Column
    private String link;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    //@JoinColumn(name = "seller_id")
    private Seller seller;
    @OneToMany(mappedBy = "advertisement")
    private List<CarParam> carParams;


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public List<CarParam> getCarParams() {
        return carParams;
    }

    public void setCarParams(List<CarParam> carParams) {
        this.carParams = carParams;
    }
}
