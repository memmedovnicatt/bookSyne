package com.example.booksyne.model.dto.response;

import lombok.Data;

@Data
public class MagazineResponseForGetAll {
    private Double price;
    private String currency;
    private String editionName;
}
