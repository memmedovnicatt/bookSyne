package com.example.booksyne.dao.repository;

import com.example.booksyne.dao.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findByAuthorId(Integer authorId);

    Optional<List<Book>> findByLanguage(String language);

    @Query("select b.stock from Book b where b.id=:bookId")
    Optional<Integer>findStockByBookId(@Param("bookId") Integer bookId);

    @Modifying
    @Query("UPDATE Book b SET b.stock = b.stock - :quantity WHERE b.id = :productId")
    void decreaseStock(@Param("productId") Integer productId, @Param("quantity") int quantity);

}