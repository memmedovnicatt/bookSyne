package com.example.booksyne.dao.entity;

import com.example.booksyne.model.enums.BasketStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BasketItem> basketItems;

    @Enumerated(EnumType.STRING)
    private BasketStatus basketStatus;

    @OneToOne(mappedBy = "basket", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment;
}