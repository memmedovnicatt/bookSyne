package com.example.booksyne.dao.repository;

import com.example.booksyne.dao.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findByAuthorId(Integer authorId);
    Optional<List<Book>> findByLanguage(String language);

}
