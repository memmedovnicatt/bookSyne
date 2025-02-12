package com.example.booksyne.dao.entity;

import com.example.booksyne.model.enums.BasketItemStatus;
import com.example.booksyne.model.enums.BasketStatus;
import com.example.booksyne.model.enums.ProductType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Entity
@ToString(exclude = "basket")  // Basket obyekti toString-də çıxarılır
public class BasketItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    private Integer productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basket_id")
    private Basket basket;

    private Integer quantity;
    private LocalDateTime addedTime;
    private Double price;
    private String currency;
}