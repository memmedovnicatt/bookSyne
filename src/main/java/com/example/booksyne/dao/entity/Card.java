package com.example.booksyne.dao.entity;

import com.example.booksyne.model.enums.CardStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cardNumber;
    private Integer cvv;
    private LocalDate expirationDate;

    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;

    @ManyToOne(cascade =CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Payment> payments;
}