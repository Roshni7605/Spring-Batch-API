package com.example.springbatchapi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Product {

    @Id
    @Column(name = "id", nullable = false)
    private Integer productId;
    private String productName;
    private String productDesc;
    private String productPrice;

    public Product() {
    }

    public Product(Integer productId, String productName, String productDesc, String productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productPrice = productPrice;
    }
}

