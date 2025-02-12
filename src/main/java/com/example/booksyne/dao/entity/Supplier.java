package com.example.booksyne.dao.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String address;
    private String mail;
    private String phoneNumber;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Gift> gifts;
}