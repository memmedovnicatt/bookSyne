package com.example.booksyne.mapper;

import com.example.booksyne.dao.entity.Favourite;
import com.example.booksyne.model.dto.request.FavouriteRequestDto;
import com.example.booksyne.model.dto.response.FavouriteDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FavouriteMapper {
    Favourite toFavourite(FavouriteRequestDto favouriteRequestDto);

    FavouriteDetailDto toFavouriteGetAllResponse(Favourite favourite);

}
