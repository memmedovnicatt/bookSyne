package com.example.booksyne.dao.entity;


import jakarta.persistence.*;
import lombok.Data;

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


}