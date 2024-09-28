package com.example.booksyne.servis.impl;

import com.example.booksyne.dao.entity.Supplier;
import com.example.booksyne.dao.repository.SupplierRepository;
import com.example.booksyne.servis.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    @Override
    public Supplier add(Supplier supplier) {
        return supplierRepository.save(supplier);
    }
}
