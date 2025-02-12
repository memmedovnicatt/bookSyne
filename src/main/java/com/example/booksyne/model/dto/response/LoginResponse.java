package com.example.booksyne.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String username;
}
