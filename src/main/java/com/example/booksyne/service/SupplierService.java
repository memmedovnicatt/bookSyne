package com.example.booksyne.service;

import com.example.booksyne.dao.entity.Supplier;
import com.example.booksyne.model.dto.request.SupplierRequestDto;
import com.example.booksyne.model.dto.request.SupplierRequestDtoForUpdate;
import com.example.booksyne.model.dto.response.SupplierDetailResponse;
import com.example.booksyne.model.dto.response.SupplierResponseDto;
import com.example.booksyne.model.dto.response.SupplierResponseForGetAll;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface SupplierService {
    SupplierResponseDto add(SupplierRequestDto supplierRequestDto);

    void delete(Integer id);

    SupplierDetailResponse getById(Integer id);

    List<SupplierResponseForGetAll> getAll();

    void update(SupplierRequestDtoForUpdate supplierRequestDtoForUpdate,Integer id);
}
