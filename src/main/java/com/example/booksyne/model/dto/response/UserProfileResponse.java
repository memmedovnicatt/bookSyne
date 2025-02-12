package com.example.booksyne.model.dto.response;

import com.example.booksyne.model.enums.Role;
import lombok.Data;

@Data
public class UserProfileResponse {
    private String fullName;
    private String birthday;
    private String email;
    private String phoneNumber;
    private Role role;
}
