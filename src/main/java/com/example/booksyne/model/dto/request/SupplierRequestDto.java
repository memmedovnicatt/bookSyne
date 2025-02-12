package com.example.booksyne.model.dto.request;

import lombok.Data;

@Data
public class SupplierRequestDto {
    private String name;
    private String address;
    private String mail;
    private Integer phoneNumber;
}
