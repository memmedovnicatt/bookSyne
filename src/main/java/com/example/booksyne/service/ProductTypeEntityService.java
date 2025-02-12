package com.example.booksyne.service;

import com.example.booksyne.model.dto.request.ProductAddDto;
import org.springframework.stereotype.Service;

@Service
public interface ProductTypeEntityService {
    void addProduct(ProductAddDto productAddDto);
}
