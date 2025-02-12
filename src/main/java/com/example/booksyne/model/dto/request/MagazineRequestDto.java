package com.example.booksyne.model.dto.request;

import com.example.booksyne.model.enums.ProductStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MagazineRequestDto {
    private Integer publisherId;
    private LocalDateTime createdAt;
    private Double price;
    private String currency;
    private String editionName;
}
