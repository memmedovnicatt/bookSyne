package com.example.booksyne.dao.entity;

import com.example.booksyne.model.enums.ProductStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.SignStyle;
import java.util.List;

@Entity
@Data
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double price;
    private String color;
    private String bestseller;
    private String currency;
    private String genre;
    private String language;
    private Integer vol;
    private Integer stock;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @JsonIgnore
    private Author author;
}