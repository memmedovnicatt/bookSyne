package com.example.booksyne.dao.repository;

import com.example.booksyne.dao.entity.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftRepository extends JpaRepository<Gift,Integer> {

}
