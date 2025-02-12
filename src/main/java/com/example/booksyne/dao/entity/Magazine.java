package com.example.booksyne.dao.entity;

import com.example.booksyne.model.enums.ProductStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "magazine")
public class Magazine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double price;
    private String currency;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private String editionName;
    private Integer stock;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id",referencedColumnName = "id")
    @ToString.Exclude
    private Publisher publisher;
}