package com.example.booksyne.mapper;

import com.example.booksyne.dao.entity.Supplier;
import com.example.booksyne.model.dto.request.SupplierRequestDto;
import com.example.booksyne.model.dto.request.SupplierRequestDtoForUpdate;
import com.example.booksyne.model.dto.response.SupplierDetailResponse;
import com.example.booksyne.model.dto.response.SupplierResponseDto;
import com.example.booksyne.model.dto.response.SupplierResponseForGetAll;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SupplierMapper {

    Supplier toSupplier(SupplierRequestDto supplierRequestDto);

    SupplierResponseDto toSupplierResponse(Supplier supplier);

    SupplierDetailResponse toSupplierDetailResponse(Supplier supplier);

    List<SupplierResponseForGetAll> toSupplierResponseForGetAll(List<Supplier> suppliers);

    void updateForEntity(SupplierRequestDtoForUpdate supplierRequestDtoForUpdate, @MappingTarget Supplier supplier);
}
