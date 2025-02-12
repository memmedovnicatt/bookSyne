package com.example.booksyne.service.impl;

import com.example.booksyne.dao.entity.*;
import com.example.booksyne.dao.repository.AuthorRepository;
import com.example.booksyne.dao.repository.BookRepository;
import com.example.booksyne.dao.repository.FavouriteRepository;
import com.example.booksyne.dao.repository.UserRepository;
import com.example.booksyne.mapper.BookMapper;
import com.example.booksyne.mapper.FavouriteMapper;
import com.example.booksyne.model.dto.request.BookRequestDto;
import com.example.booksyne.model.dto.request.FavouriteRequestDto;
import com.example.booksyne.model.dto.response.BookLanguageDto;
import com.example.booksyne.model.dto.response.BookResponseDto;
import com.example.booksyne.model.dto.response.BookResponseFeature;
import com.example.booksyne.model.dto.response.BookResponseForAuthor;
import com.example.booksyne.model.exception.child.*;
import com.example.booksyne.service.BookService;
import com.example.booksyne.utility.FavouriteUtilityService;
import com.example.booksyne.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;


    @Override
    public BookResponseDto add(BookRequestDto bookRequestDto) {
        log.info("Adding a new book with request data: {}", bookRequestDto);
        Author author = authorRepository.findById(bookRequestDto.getAuthorId())
                .orElseThrow(() ->
                        new NotFoundException("Author with id not found " + bookRequestDto.getAuthorId()));
        log.info("Author is found");
        Book book = bookMapper.toBook(bookRequestDto);
        book.setStock(100);

        Book savedBook = bookRepository.save(book);
        log.info("Book saved successfully with id: {} ", savedBook.getId());

        book.setAuthor(author);
        return bookMapper.toBookResponse(savedBook);
    }

    @Override
    public void delete(Integer id) {
        log.info("Deleting book with id: {}", id);
        if (!bookRepository.existsById(id)) {
            throw new NotFoundException("Book with id: {} " + id);
        }
        bookRepository.deleteById(id);
        log.info("Book with id: {} deleted successfully", id);
    }

    @Override
    public void update(BookRequestDto bookRequestDto, Integer id) {
        log.info("Update method for book with id: {} is started", id);
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found with id " + id));
        log.info("Book found with id: {}", id);
        Author author = authorRepository.findById(bookRequestDto.getAuthorId())
                .orElseThrow(() -> new NotFoundException("Author not found with id " + id));

        log.info("Author found with id: {}", bookRequestDto.getAuthorId());
        book.setAuthor(author);

        bookMapper.updateEntityField(bookRequestDto, book);
        bookRepository.save(book);

        log.info("Book with id: {} updated successfully", id);
    }

    @Override
    public List<BookResponseForAuthor> getBooksByAuthorId(Integer authorId) {
        log.info("Searching books for author with id: {}", authorId);

        List<Book> books = bookRepository.findByAuthorId(authorId);
        if (books.isEmpty()) {
            throw new NotFoundException("This book not found for author with id:" + authorId);
        }
        log.info("Found {} books for author with id {}", books.size(), authorId);
        return bookMapper.toBookForAuthor(books);
    }

    @Override
    public List<BookLanguageDto> getBooksByLanguage(String language) {
        log.info("Searching books for {} language", language);
        List<Book> books = bookRepository.findByLanguage(language)
                .orElseThrow(() -> new NotFoundException(language + " is not found"));
        if (books.isEmpty()) {
            throw new NotFoundException(language + " is not found");
        }
        log.info("Found {} books with {} language", books.size(), language);
        return bookMapper.toBookLanguage(books);
    }

    @Override
    public BookResponseFeature getById(Integer id) {
        log.info("Searching for book with id: {}", id);
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found with id " + id));
        log.info("Found book with id: {}", id);
        return bookMapper.toBookResponseFeature(book);
    }
}