package com.example.booksyne.dao.repository;

import com.example.booksyne.dao.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
}
