package com.example.booksyne.dao.entity;

import com.example.booksyne.model.enums.ProductType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProductTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    private String serviceName;
    private String methodName;
}
