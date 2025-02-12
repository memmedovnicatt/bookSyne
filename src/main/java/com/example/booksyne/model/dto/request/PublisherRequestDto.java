package com.example.booksyne.model.dto.request;

import lombok.Data;

@Data
public class PublisherRequestDto {
    private String name;
    private String address;
    private String email;
    private String organizationType;
    private String phoneNumber;
    private String establishmentDate;
    private String websiteUrl;
}
