package com.example.booksyne.model.dto.request;

import lombok.Data;

@Data
public class PublisherUpdateDto {
    private String name;
    private String address;
    private String email;
    private String establishmentDate;
    private String organizationType;
    private String phoneNumber;
}