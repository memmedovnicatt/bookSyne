package com.example.booksyne.mapper;

import com.example.booksyne.dao.entity.Basket;
import com.example.booksyne.dao.entity.BasketItem;
import com.example.booksyne.dao.entity.User;
import com.example.booksyne.model.dto.request.BasketRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BasketMapper {
    Basket toBasket(BasketRequestDto basketRequestDto);
}
