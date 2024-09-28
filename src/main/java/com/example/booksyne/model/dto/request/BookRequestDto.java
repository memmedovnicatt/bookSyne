package com.example.booksyne.model.dto.request;

import lombok.Data;

@Data
public class BookRequestDto {
    private Integer authorId;
    private String name;
    private Double price;
    private String color;
    private String bestseller;
    private String currency;
    private String genre;
    private String language;
    private Integer vol;
}
