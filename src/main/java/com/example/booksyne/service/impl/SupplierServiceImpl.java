package com.example.booksyne.service.impl;

import com.example.booksyne.dao.entity.Supplier;
import com.example.booksyne.dao.repository.SupplierRepository;
import com.example.booksyne.mapper.SupplierMapper;
import com.example.booksyne.model.dto.request.SupplierRequestDto;
import com.example.booksyne.model.dto.request.SupplierRequestDtoForUpdate;
import com.example.booksyne.model.dto.response.SupplierDetailResponse;
import com.example.booksyne.model.dto.response.SupplierResponseDto;
import com.example.booksyne.model.dto.response.SupplierResponseForGetAll;
import com.example.booksyne.model.exception.child.NotFoundException;
import com.example.booksyne.service.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;


    @Override
    public SupplierResponseDto add(SupplierRequestDto supplierRequestDto) {
        log.info("Adding method started with request data: {}", supplierRequestDto);
        Supplier supplier = supplierMapper.toSupplier(supplierRequestDto);
        log.info("Successfully adding");
        Supplier savedSupplier = supplierRepository.save(supplier);
        log.info("Successfully saved");
        return supplierMapper.toSupplierResponse(savedSupplier);
    }

    @Override
    public void delete(Integer id) {
        log.info("Deleting method started with id:{}", id);
        if (!supplierRepository.existsById(id)) {
            throw new NotFoundException("This supplier not found with id:" + id);
        }
        log.info("Supplier is found");
        supplierRepository.deleteById(id);
        log.info("Supplier is deleted");
    }

    @Override
    public SupplierDetailResponse getById(Integer id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("This supplier not found with id:" + id));
        log.info("Found with id:" + id);
        log.info("getById method is done");
        return supplierMapper.toSupplierDetailResponse(supplier);
    }

    @Override
    public List<SupplierResponseForGetAll> getAll() {
        log.info("Method getAll started");
        List<Supplier> supplier = supplierRepository.findAll();
        log.info("Suppliers fetched, total count: {}", supplier.size());
        log.info("Mapping to SupplierResponseForGetAll completed");
        return supplierMapper.toSupplierResponseForGetAll(supplier);
    }

    @Override
    public void update(SupplierRequestDtoForUpdate supplierRequestDtoForUpdate, Integer id) {
        log.info("Update method for supplier with id: {} is started", id);
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("This supplier not found with id:" + id));
        log.info("Found id {} for supplier", id);
        supplierMapper.updateForEntity(supplierRequestDtoForUpdate, supplier);
        supplierRepository.save(supplier);
        log.info("Supplier with id: {} updated successfully", id);
    }
}