package com.example.booksyne.model.dto.request;

import lombok.Data;

@Data
public class BagRequestDto {
    private String type;
    private Double price;
    private String size;
    private String brand;
    private String material;
    private String currency;

}
