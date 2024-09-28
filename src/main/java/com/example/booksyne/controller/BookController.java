package com.example.booksyne.controller;

import com.example.booksyne.dao.entity.Book;
import com.example.booksyne.dao.repository.BookRepository;
import com.example.booksyne.model.dto.request.BookRequestDto;
import com.example.booksyne.model.dto.response.BookLanguageDto;
import com.example.booksyne.model.dto.response.BookResponseDto;
import com.example.booksyne.model.dto.response.BookResponseFeature;
import com.example.booksyne.model.dto.response.BookResponseForAuthor;
import com.example.booksyne.model.exception.child.BookLanguageNotException;
import com.example.booksyne.servis.BookService;
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
    private final BookRepository bookRepository;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookResponseDto> add(@RequestBody BookRequestDto bookRequestDto) {
        BookResponseDto bookResponseDto = bookService.add(bookRequestDto);
        return ResponseEntity.ok(bookResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        bookService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody BookRequestDto bookRequestDto) {
        bookService.update(bookRequestDto, id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/uploadPdf/{id}")
    public ResponseEntity<HttpStatus> uploadPdf(@PathVariable Integer id, @RequestParam("file") MultipartFile file) {
        bookService.uploadPdf(id, file);
        return ResponseEntity.ok(HttpStatus.CREATED);

    }

    @GetMapping("/downloadPdf/{id}")
    public ResponseEntity<Void> downloadPdf(@PathVariable Integer id) {
        bookService.downloadPdf(id);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<BookResponseForAuthor>> getBooksByAuthorId(@PathVariable Integer authorId) {
        List<BookResponseForAuthor> books = bookService.getBooksByAuthorId(authorId);
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/language/{language}")
    public ResponseEntity<List<BookLanguageDto>> getLanguageBooks(@PathVariable String language) {
        List<BookLanguageDto> books = bookService.getBooksByLanguage(language);
        if (books.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(books);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<BookResponseFeature> getById(@PathVariable Integer id){
        BookResponseFeature bookResponseFeature=bookService.getById(id);
        return ResponseEntity.ok(bookResponseFeature);
    }
}