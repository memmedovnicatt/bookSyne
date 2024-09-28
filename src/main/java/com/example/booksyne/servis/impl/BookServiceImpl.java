package com.example.booksyne.servis.impl;

import com.example.booksyne.dao.entity.Author;
import com.example.booksyne.dao.entity.Book;
import com.example.booksyne.dao.repository.AuthorRepository;
import com.example.booksyne.dao.repository.BookRepository;
import com.example.booksyne.mapper.BookMapper;
import com.example.booksyne.model.dto.request.BookRequestDto;
import com.example.booksyne.model.dto.response.BookLanguageDto;
import com.example.booksyne.model.dto.response.BookResponseDto;
import com.example.booksyne.model.dto.response.BookResponseFeature;
import com.example.booksyne.model.dto.response.BookResponseForAuthor;
import com.example.booksyne.model.exception.child.AuthorNotFoundException;
import com.example.booksyne.model.exception.child.BookLanguageNotException;
import com.example.booksyne.model.exception.child.BookNotFoundException;
import com.example.booksyne.servis.BookService;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    @Value("${file.upload-dir}")  // Yükləmə qovluğu application.properties-dən gələcək
    private String uploadDir;

    @Override
    public BookResponseDto add(BookRequestDto bookRequestDto) {
        Author author = authorRepository.findById(bookRequestDto.getAuthorId())
                .orElseThrow(() ->
                        new AuthorNotFoundException("Book with id " + bookRequestDto.getAuthorId()));
        Book book = bookMapper.toBook(bookRequestDto);
        Book savedBook = bookRepository.save(book);
        book.setAuthor(author);
        return bookMapper.toBookResponse(savedBook);
    }

    @Override
    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void update(BookRequestDto bookRequestDto, Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id " + id));

        Author author = authorRepository.findById(bookRequestDto.getAuthorId())
                .orElseThrow(() -> new AuthorNotFoundException("Author not found with id " + id));

        book.setAuthor(author);
        bookMapper.updateEntityField(bookRequestDto, book);
        bookRepository.save(book);
    }

    @Override
    public List<BookResponseForAuthor> getBooksByAuthorId(Integer authorId) {
       List<Book> books=bookRepository.findByAuthorId(authorId);
       return bookMapper.toBookForAuthor(books);
    }

    @Override
    public List<BookLanguageDto>  getBooksByLanguage(String language) {
        List<Book> books=bookRepository.findByLanguage(language)
        .orElseThrow(()->new BookLanguageNotException(language+" is not found"));

        if (books.isEmpty()) {
            throw new BookLanguageNotException(language + " is not found");
        }
        return bookMapper.toBookLanguage(books);
    }

    @Override
    public BookResponseFeature getById(Integer id) {
        Book book=bookRepository.findById(id)
                .orElseThrow(()->new BookNotFoundException("Book not found with id "+id));
        return bookMapper.toBookResponseFeature(book);
    }


    @Override
    public void uploadPdf(Integer id, MultipartFile file) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with " + id));

        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);

        try {
            Files.write(filePath, file.getBytes());
            book.setPdfFilePath(filePath.toString());
            bookRepository.save(book);
            log.info("PDF file uploaded successfully");

        } catch (IOException e) {
            log.info("Failed to download file");
        }
    }

    @Override
    public ResponseEntity<Resource> downloadPdf(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        try {
            Path pdfPath = Paths.get(book.getPdfFilePath());
            Resource resource = new UrlResource(pdfPath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pdfPath.getFileName() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.status(404).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }




}