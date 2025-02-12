package com.example.booksyne.mapper;

import com.example.booksyne.dao.entity.Magazine;
import com.example.booksyne.model.dto.request.MagazineDtoForUpdate;
import com.example.booksyne.model.dto.request.MagazineRequestDto;
import com.example.booksyne.model.dto.response.MagazineInfoResponse;
import com.example.booksyne.model.dto.response.MagazineResponse;
import com.example.booksyne.model.dto.response.MagazineResponseForGetAll;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MagazineMapper {

    @Mapping(source = "publisherId", target = "publisher.id")
    Magazine toMagazine(MagazineRequestDto magazineRequestDto);

    @Mapping(source = "publisher.id", target = "publisherId")
    MagazineResponse toMagazineResponse(Magazine magazine);

    @Mapping(source = "publisher.name", target = "publisherName")
    @Mapping(source = "publisher.address", target = "publisherAddress")
    @Mapping(source = "publisher.phoneNumber", target = "publisherPhoneNumber")
    @Mapping(source = "publisher.email", target = "publisherEmail")
    MagazineInfoResponse toMagazineInfoResponse(Magazine magazine);

    MagazineResponseForGetAll toMagazineResponseGetAll(Magazine magazine);

    void updateEntityField(MagazineDtoForUpdate magazineDtoForUpdate, @MappingTarget Magazine magazine);
}
