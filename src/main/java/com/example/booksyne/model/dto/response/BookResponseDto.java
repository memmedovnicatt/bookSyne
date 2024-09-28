package com.example.booksyne.model.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BookResponseDto {
    private Integer authorId;
    private String name;
}
