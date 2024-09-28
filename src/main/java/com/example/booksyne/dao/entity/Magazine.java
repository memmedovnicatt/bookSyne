package com.example.booksyne.dao.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "magazine")
public class Magazine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String issue_number;

    private String price;
    private String currency;


}
