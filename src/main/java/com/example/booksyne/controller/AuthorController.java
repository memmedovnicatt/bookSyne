package com.example.booksyne.controller;

import com.example.booksyne.dao.entity.Book;
import com.example.booksyne.model.dto.request.AuthorRequestDto;
import com.example.booksyne.model.dto.response.AuthorResponseDto;
import com.example.booksyne.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Add a new Author")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthorResponseDto> add(@RequestBody AuthorRequestDto authorRequestDto) {
        AuthorResponseDto authorResponse = authorService.add(authorRequestDto);
        return ResponseEntity.ok(authorResponse);
    }

    @Operation(summary = "Update an existing author")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody AuthorRequestDto authorRequestDto, @PathVariable Integer id) {
        authorService.update(authorRequestDto, id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Delete an author by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        authorService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get a list of books for a specific author by their name")
    @GetMapping("/{authorName}")
    public ResponseEntity<List<Book>> getBooks(@PathVariable String authorName) {
        List<Book> books = authorService.getBooksByAuthorName(authorName);
        return ResponseEntity.ok(books);
    }
}