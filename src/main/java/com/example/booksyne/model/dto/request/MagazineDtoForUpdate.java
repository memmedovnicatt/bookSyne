package com.example.booksyne.model.dto.request;

import lombok.Data;

@Data
public class MagazineDtoForUpdate {
    private Double price;
    private Integer publisherId;
    private String currency;
    private String editionName;
}
