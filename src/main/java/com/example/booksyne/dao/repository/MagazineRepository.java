package com.example.booksyne.dao.repository;

import com.example.booksyne.dao.entity.Magazine;
import com.example.booksyne.model.enums.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine, Integer> {
    Page<Magazine> findByStatus(Pageable pageable, ProductStatus productStatus);

    @Query("select m.stock from Magazine m where m.id=:magazineId")
    Optional<Integer> findStockByMagazineId(@Param("magazineId") Integer magazineId);

    @Modifying
    @Query("UPDATE Magazine m SET m.stock = m.stock - :quantity WHERE m.id = :productId")
    void decreaseStock(@Param("productId") Integer productId, @Param("quantity") int quantity);
}
