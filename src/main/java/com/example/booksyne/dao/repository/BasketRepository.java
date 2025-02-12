package com.example.booksyne.dao.repository;

import com.example.booksyne.dao.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.tags.form.SelectTag;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Integer> {
    Optional<Basket> findByUserId(Integer userId);

    boolean existsByUserId(Integer userId);
}