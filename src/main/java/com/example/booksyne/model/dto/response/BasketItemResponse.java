package com.example.booksyne.model.dto.response;

import com.example.booksyne.model.enums.ProductType;
import lombok.Data;

@Data
public class BasketItemResponse {
    private ProductType productType;
    private Integer productId;
    private Double price;
    private String currency;
    private Integer quantity;
    private String type;
}