package com.example.booksyne.controller;

import com.example.booksyne.dao.entity.Author;
import com.example.booksyne.dao.entity.Book;
import com.example.booksyne.model.dto.request.AuthorRequestDto;
import com.example.booksyne.model.dto.response.AuthorResponse;
import com.example.booksyne.model.dto.response.AuthorResponseDto;
import com.example.booksyne.model.exception.child.AuthorNotFoundException;
import com.example.booksyne.servis.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthorResponseDto> add(@RequestBody AuthorRequestDto authorRequestDto) {
        AuthorResponseDto authorResponse = authorService.add(authorRequestDto);
        return ResponseEntity.ok(authorResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody AuthorRequestDto authorRequestDto, @PathVariable Integer id) {
        authorService.update(authorRequestDto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        authorService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{authorName}")
    public ResponseEntity<List<Book>> getBooks(@PathVariable String authorName) {
        List<Book> books = authorService.getBooks(authorName);
        if (books.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(books);
    }

//    @ExceptionHandler(AuthorNotFoundException.class)
//    public ResponseEntity<String> handleAuthorNotFoundException(AuthorNotFoundException ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//    }

}

