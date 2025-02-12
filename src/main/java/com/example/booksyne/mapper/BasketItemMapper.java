package com.example.booksyne.mapper;

import com.example.booksyne.dao.entity.Basket;
import com.example.booksyne.dao.entity.BasketItem;
import com.example.booksyne.dao.entity.User;
import com.example.booksyne.model.dto.request.BasketItemRequestDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BasketItemMapper {

    BasketItem toBasketItem(BasketItemRequestDto basketItemRequestDto);
}
