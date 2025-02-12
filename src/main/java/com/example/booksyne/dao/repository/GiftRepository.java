package com.example.booksyne.dao.repository;

import com.example.booksyne.dao.entity.Gift;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GiftRepository extends JpaRepository<Gift, Integer> {

    @Query("select g.stock from Gift g where g.id=:giftId")
    Optional<Integer> findStockByGiftId(@Param("giftId") Integer giftId);

    @Modifying
    @Query("UPDATE Gift g SET g.stock = g.stock - :quantity WHERE g.id = :productId")
    void decreaseStock(@Param("productId") Integer productId, @Param("quantity") int quantity);
}
