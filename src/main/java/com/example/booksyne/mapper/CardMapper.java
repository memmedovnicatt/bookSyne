package com.example.booksyne.mapper;

import com.example.booksyne.dao.entity.Card;
import com.example.booksyne.model.dto.request.CardRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy =
        NullValuePropertyMappingStrategy.IGNORE)

public interface CardMapper {
    Card toCard(CardRequestDto cardRequestDto);
}
