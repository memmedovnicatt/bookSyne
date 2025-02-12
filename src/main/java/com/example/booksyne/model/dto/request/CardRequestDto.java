package com.example.booksyne.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;


@Data
@ToString
public class CardRequestDto {

    @NotBlank(message = "Card number cannot be null")
    @Pattern(regexp = "\\d{16}", message = "Card number must be exactly 16 digits")
    private String cardNumber;

    @NotBlank(message = "CVV cannot be null")
    @Size(min = 3, max = 3, message = "CVV must be exactly 3 digits")
    private String cvv;

    @NotNull(message = "Expiration date cannot be null")
    private LocalDate expirationDate;
}