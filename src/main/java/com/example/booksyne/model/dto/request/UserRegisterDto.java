package com.example.booksyne.model.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserRegisterDto {

    @NotEmpty(message = "Full name cannot be null or empty")
    @Size(max = 100, message = "Full name must be at most 100 characters long")
    @Column(columnDefinition = "VARCHAR(255)")
    private String fullName;

    @NotEmpty(message = "Username must not be empty")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Column(columnDefinition = "VARCHAR(255)")
    private String username;

    @NotEmpty(message = "Email must not be empty")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Email must be valid.Example: firstname-lastname@example.com  ")
    @Column(columnDefinition = "VARCHAR(255)")
    @Size(min = 6, max = 70, message = "Email must be between 6 and 70 characters")
    private String email;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Birthday must be in the format YYYY-MM-DD")
    @NotEmpty(message = "Birthday cannot be null or empty")
    private String birthday;

    @Size(min = 3, max = 30, message = "Password must be between 3 adn 30 characters")
    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @NotEmpty(message = "Phone must not be empty")
    @Pattern(regexp = "^\\+994(50|51|55|70|77|99)\\d{7}$", message = "Phone number must be valid '+9940000000000'.Example: +994516125092")
    @Column(columnDefinition = "VARCHAR(20)")
    @Size(min = 10, max = 20, message = "Phone must be between 10 and 20 characters")
    private String phoneNumber;
}