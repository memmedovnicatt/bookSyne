package com.example.booksyne.model.dto.request;

import com.example.booksyne.model.enums.BasketStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BasketRequestDto {
    private LocalDateTime createdAt;
    private BasketStatus basketStatus;
}
