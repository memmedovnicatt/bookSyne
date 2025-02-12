package com.example.booksyne.dao.repository;

import com.example.booksyne.dao.entity.BasketItem;

import com.example.booksyne.model.dto.response.BasketItemResponse;
import com.example.booksyne.model.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasketItemRepository extends JpaRepository<BasketItem, Integer> {

    List<BasketItemResponse> getProductsByBasketId(Integer basketId);

    List<BasketItem> deleteByBasketId(Integer basketId);

    BasketItem findByBasketIdAndProductId(Integer basketId,Integer productId);


    @Query("select sum(b.price) from BasketItem b where b.basket.id=:basketId ")
    Double findTotalAmountByBasketId(@Param("basketId") Integer basketId);

    List<BasketItem> findByBasketId(Integer basketId);
}