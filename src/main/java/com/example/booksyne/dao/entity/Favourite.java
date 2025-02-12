package com.example.booksyne.dao.entity;

import com.example.booksyne.model.enums.ProductType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Favourite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "addedAt")
    private LocalDateTime addedAt;

    private Integer productId;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @ManyToOne(cascade =CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}