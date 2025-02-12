package com.example.booksyne.model.dto.response;

import com.example.booksyne.model.enums.ProductType;
import lombok.Data;

@Data
public class FavouriteDetailDto {
    private Integer productId;
    private ProductType productType;
    private String currency;
    private Double price;
    private String type;
}
