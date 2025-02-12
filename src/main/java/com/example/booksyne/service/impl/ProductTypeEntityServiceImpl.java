package com.example.booksyne.service.impl;

import com.example.booksyne.dao.entity.ProductTypeEntity;
import com.example.booksyne.dao.repository.ProductTypeEntityRepository;
import com.example.booksyne.mapper.ProductTypeMapper;
import com.example.booksyne.model.dto.request.ProductAddDto;
import com.example.booksyne.service.ProductTypeEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductTypeEntityServiceImpl implements ProductTypeEntityService {
    private final ProductTypeEntityRepository productTypeEntityRepository;
    private final ProductTypeMapper productTypeMapper;

    @Override
    public void addProduct(ProductAddDto productAddDto) {
        log.info("Method addProduct was started with this request:{}",productAddDto);
        ProductTypeEntity productType=productTypeMapper.toProductTypeEntity(productAddDto);
        ProductTypeEntity savedProductType= productTypeEntityRepository.save(productType);
        log.info("Saved successfully");
    }
}