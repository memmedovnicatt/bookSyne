package com.example.booksyne.dao.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String address;

}
