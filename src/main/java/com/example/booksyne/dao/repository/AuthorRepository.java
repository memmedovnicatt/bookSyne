package com.example.booksyne.dao.repository;

import com.example.booksyne.dao.entity.Author;
import com.example.booksyne.dao.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findByName(String name);

}
