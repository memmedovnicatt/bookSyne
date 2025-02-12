package com.example.booksyne.model.dto.request;

import com.example.booksyne.model.enums.ProductType;
import lombok.Data;

@Data
public class DeleteFavouriteRequest {
    private Integer productId;
    private ProductType productType;
}
