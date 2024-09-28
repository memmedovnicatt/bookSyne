package com.example.booksyne.servis;

import com.example.booksyne.dao.entity.Supplier;
import org.springframework.stereotype.Service;

@Service
public interface SupplierService {
    Supplier add(Supplier supplier);
}
