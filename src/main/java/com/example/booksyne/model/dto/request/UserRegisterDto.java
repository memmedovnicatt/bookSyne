package com.example.booksyne.model.dto.request;

import lombok.Data;

@Data
public class UserRegisterDto {
    private String fullName;
    private String username;
    private String email;
    private String birthday;
    private String role;
    private String password;
    private String phoneNumber;
}
