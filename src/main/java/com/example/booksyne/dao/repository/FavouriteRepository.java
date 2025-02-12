package com.example.booksyne.dao.repository;

import com.example.booksyne.dao.entity.Favourite;
import com.example.booksyne.model.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Integer> {
    Optional<Favourite> findByUserIdAndProductIdAndProductType(Integer userId, Integer productId, ProductType productType);

    void deleteByUserIdAndProductIdAndProductType(Integer userId, Integer productId, ProductType productType);

    //a method that returns a page always accepts a pageable
    List<Favourite> findByUserId(Integer userId);

}
