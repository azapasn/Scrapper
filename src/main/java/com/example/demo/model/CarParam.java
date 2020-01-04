package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CarParam {
    @Id
    @GeneratedValue
    @Column
    private long id;
    @Column
    private String name;
    @Column
    private String value;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "advertisment_id")
    private Advertisement advertisement;

    public CarParam(String name, String value) {
        this.name = name;
        this.value = value;
    }
    public CarParam(){

    }
    public CarParam(String name, String value, Long id, Advertisement advertisement) {
        this.name = name;
        this.value = value;
        this.id = id;
        this.advertisement = advertisement;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }
}
