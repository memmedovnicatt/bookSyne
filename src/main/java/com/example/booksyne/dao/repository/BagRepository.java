package com.example.booksyne.dao.repository;

import com.example.booksyne.dao.entity.Bag;

import com.example.booksyne.model.dto.response.BagInfoResponse;
import com.example.booksyne.model.dto.response.BagResponse;
import com.example.booksyne.model.enums.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BagRepository extends JpaRepository<Bag, Integer> {

    Optional<List<Bag>> findByType(String type);

    Page<Bag> findByStatus(Pageable pageable, ProductStatus status);

    @Query("select b.stock from Bag b where b.id=:bagId")
    Optional<Integer> findStockByBagId(@Param("bagId") Integer bagId);

    @Modifying
    @Query("UPDATE Bag b SET b.stock = b.stock - :quantity WHERE b.id = :productId")
    void decreaseStock(@Param("productId") Integer productId, @Param("quantity") int quantity);
}