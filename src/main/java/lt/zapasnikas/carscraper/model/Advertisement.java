package lt.zapasnikas.carscraper.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Advertisement {
    @Id
    @Column
    private String id;
    @Column
    private String link;
    @JsonIgnore
    @ManyToOne
    private Seller seller;
    @Column
    private int price;

    @Column(insertable = false, updatable = false)
    private LocalDateTime creationDate;


    @OneToOne(mappedBy = "advertisement", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CarParam carParam;


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


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

    public CarParam getCarParam() {
        return carParam;
    }

    public void setCarParams(CarParam carParam) {
        this.carParam = carParam;
    }

    public String toString() {
        return getId();
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

}
