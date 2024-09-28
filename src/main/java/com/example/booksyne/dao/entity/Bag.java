package com.example.booksyne.dao.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bags")
public class Bag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type;
    private Double price;
    private String size;
    private String brand;
    private String material;
    private String currency;

}
