package com.example.booksyne.controller;

import com.example.booksyne.dao.entity.User;
import com.example.booksyne.dao.repository.BookRepository;
import com.example.booksyne.model.dto.request.BookRequestDto;
import com.example.booksyne.model.dto.request.FavouriteRequestDto;
import com.example.booksyne.model.dto.response.BookLanguageDto;
import com.example.booksyne.model.dto.response.BookResponseDto;
import com.example.booksyne.model.dto.response.BookResponseFeature;
import com.example.booksyne.model.dto.response.BookResponseForAuthor;
import com.example.booksyne.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;


    @Operation(summary = "Add a new book")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookResponseDto> add(@RequestBody BookRequestDto bookRequestDto) {
        BookResponseDto bookResponseDto = bookService.add(bookRequestDto);
        return ResponseEntity.ok(bookResponseDto);
    }

    @Operation(summary = "Delete a book by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        bookService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update an existing book")
    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody BookRequestDto bookRequestDto) {
        bookService.update(bookRequestDto, id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get all books written by a specific author")
    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<BookResponseForAuthor>> getBooksByAuthorId(@PathVariable Integer authorId) {
        List<BookResponseForAuthor> books = bookService.getBooksByAuthorId(authorId);
        return ResponseEntity.ok(books);
    }

    @Operation(summary = "Get all books for a specific language")
    @GetMapping("/language/{language}")
    public ResponseEntity<List<BookLanguageDto>> getLanguageBooks(@PathVariable String language) {
        List<BookLanguageDto> books = bookService.getBooksByLanguage(language);
        return ResponseEntity.ok(books);
    }

    @Operation(summary = "Get details of a book by its ID")
    @GetMapping("/id/{id}")
    public ResponseEntity<BookResponseFeature> getById(@PathVariable Integer id) {
        BookResponseFeature bookResponseFeature = bookService.getById(id);
        return ResponseEntity.ok(bookResponseFeature);
    }
}