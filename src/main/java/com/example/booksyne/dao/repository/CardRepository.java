package com.example.booksyne.dao.repository;

import com.example.booksyne.dao.entity.Basket;
import com.example.booksyne.dao.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Integer> {
    Card findByCardNumber(String cardNumber);

    Optional<List<Card>> findByUserId(Integer userId);
}