package com.example.booksyne.model.dto.request;

import lombok.Data;

@Data
public class GiftRequestForUpdateDto {
    private Integer supplierId;
    private String name;
    private Double price;
    private String color;
    private String currency;
}
