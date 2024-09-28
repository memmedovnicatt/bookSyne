package com.example.booksyne.model.dto.response;

import lombok.Data;

@Data
public class BookResponseFeature {
    private String genre;
    private int vol;
    private boolean bestSeller;
    private String color;
}
