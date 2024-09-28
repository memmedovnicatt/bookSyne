package com.example.booksyne.dao.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String nationality;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    private List<Book> books;
}
