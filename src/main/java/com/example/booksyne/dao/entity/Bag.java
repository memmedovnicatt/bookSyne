package com.example.booksyne.dao.entity;

import com.example.booksyne.model.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "bags")
public class Bag{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type;
    private Double price;
    private String size;
    private String brand;
    private String material;
    private String currency;
    private Integer stock;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;
}