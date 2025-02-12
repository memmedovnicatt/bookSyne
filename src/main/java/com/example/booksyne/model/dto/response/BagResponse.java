package com.example.booksyne.model.dto.response;

import com.example.booksyne.model.enums.ProductStatus;
import lombok.Data;

@Data
public class BagResponse {
    private Integer id;
    private String type;
    private Double price;
    private String currency;
}