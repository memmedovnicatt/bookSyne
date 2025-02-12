package com.example.booksyne.mapper;

import com.example.booksyne.dao.entity.ProductTypeEntity;
import com.example.booksyne.model.dto.request.ProductAddDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductTypeMapper {
    ProductTypeEntity toProductTypeEntity(ProductAddDto productAddDto);
}
