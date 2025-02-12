package com.example.booksyne.model.dto.request;

import lombok.Data;

@Data
public class GiftRequestDto {
    private Integer supplierId;
    private String name;
    private String color;
    private Double price;
    private String currency;
}
