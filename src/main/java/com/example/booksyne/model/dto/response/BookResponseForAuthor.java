package com.example.booksyne.model.dto.response;

import lombok.Data;

@Data
public class BookResponseForAuthor {
    private int id;
    private String name;
    private Double price;
    private String currency;
}
