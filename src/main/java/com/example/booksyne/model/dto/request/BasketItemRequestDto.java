package com.example.booksyne.model.dto.request;

import com.example.booksyne.model.enums.ProductType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BasketItemRequestDto {
    private Integer productId;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    private Integer quantity;

    private LocalDateTime addedTime;
}