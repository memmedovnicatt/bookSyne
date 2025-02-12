package com.example.booksyne.model.dto.request;

import lombok.Data;

@Data
public class SupplierRequestDtoForUpdate {
    private String name;
    private String address;
    private String mail;
    private String phoneNumber;
}
