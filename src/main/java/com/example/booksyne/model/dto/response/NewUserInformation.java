package com.example.booksyne.model.dto.response;

import lombok.Data;

@Data
public class NewUserInformation {
    private String email;
    private String fullName;
    private String phoneNumber;
    private String username;
}
