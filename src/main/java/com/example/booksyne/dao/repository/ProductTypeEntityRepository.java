package com.example.booksyne.dao.repository;

import com.example.booksyne.dao.entity.ProductTypeEntity;
import com.example.booksyne.model.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductTypeEntityRepository extends JpaRepository<ProductTypeEntity, Integer> {
    Optional<ProductTypeEntity> findByProductType(ProductType productType);
}
