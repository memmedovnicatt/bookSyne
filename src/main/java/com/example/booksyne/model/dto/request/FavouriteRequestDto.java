package com.example.booksyne.model.dto.request;

import com.example.booksyne.model.enums.ProductType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FavouriteRequestDto {
    private LocalDateTime addedAt;
    private ProductType productType;
}