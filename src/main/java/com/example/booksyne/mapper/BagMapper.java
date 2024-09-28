package com.example.booksyne.mapper;

import com.example.booksyne.dao.entity.Bag;
import com.example.booksyne.model.dto.request.BagRequestDto;
import com.example.booksyne.model.dto.response.BagInfoResponse;
import com.example.booksyne.model.dto.response.BagResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BagMapper {
    Bag toBag(BagRequestDto bagRequestDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "price", target = "price")
    BagResponse toBagResponse(Bag bag);

    void updateEntityField(BagRequestDto bagRequestDto, @MappingTarget Bag bag);

    BagInfoResponse toBagInfoResponse(Bag bag);

}
