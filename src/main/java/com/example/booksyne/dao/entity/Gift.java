package com.example.booksyne.dao.entity;


import com.example.booksyne.model.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "gifts")
public class Gift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String color;
    private Double price;
    private String currency;
    private Integer stock;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;
}