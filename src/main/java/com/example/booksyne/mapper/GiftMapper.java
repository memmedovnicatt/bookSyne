package com.example.booksyne.mapper;

import com.example.booksyne.dao.entity.Gift;
import com.example.booksyne.model.dto.request.GiftRequestDto;
import com.example.booksyne.model.dto.request.GiftRequestForUpdateDto;
import com.example.booksyne.model.dto.response.GiftDetailByAddress;
import com.example.booksyne.model.dto.response.GiftInfoResponse;
import com.example.booksyne.model.dto.response.GiftResponseDto;
import com.example.booksyne.model.dto.response.GiftResponseForGetAll;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface GiftMapper {

    Gift toGift(GiftRequestDto giftRequestDto);

    GiftResponseDto toGiftResponseDto(Gift gift);

    GiftInfoResponse toGiftInfoResponse(Gift gift);

    List<GiftDetailByAddress> toGiftDetail(List<Gift> gift);

    void updateEntityField(GiftRequestForUpdateDto giftRequestForUpdateDto, @MappingTarget Gift gift);

    @Mapping(source = "price", target = "price")
    @Mapping(source = "currency", target = "currency")
    GiftResponseForGetAll toGiftResponseForGetAll(Gift gift);
}
