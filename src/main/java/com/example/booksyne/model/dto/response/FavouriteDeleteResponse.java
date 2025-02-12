package com.example.booksyne.model.dto.response;

import com.example.booksyne.model.enums.ProductType;
import lombok.Data;

@Data
public class FavouriteDeleteResponse {
    private Integer productId;
    private ProductType productType;
}
