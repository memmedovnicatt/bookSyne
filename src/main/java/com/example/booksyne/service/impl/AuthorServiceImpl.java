package com.example.booksyne.service.impl;

import com.example.booksyne.dao.entity.Author;
import com.example.booksyne.dao.entity.Book;
import com.example.booksyne.dao.repository.AuthorRepository;
import com.example.booksyne.mapper.AuthorMapper;
import com.example.booksyne.model.dto.request.AuthorRequestDto;
import com.example.booksyne.model.dto.response.AuthorResponseDto;
import com.example.booksyne.model.exception.child.NotFoundException;
import com.example.booksyne.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public AuthorResponseDto add(AuthorRequestDto authorRequestDto) {
        log.info("Adding author with request data : {} and {}", authorRequestDto.getAuthorName(), authorRequestDto.getAuthorNationality());
        Author author = authorMapper.toAuthor(authorRequestDto);
        Author savedAuthor = authorRepository.save(author);
        log.info("Author saved successfully");
        return authorMapper.toAuthorResponse(savedAuthor);
    }

    @Override
    public void update(AuthorRequestDto authorRequestDto, Integer id) {
        log.info("Update method for author with id: {} is started", id);
        Author author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author not found with id " + id));
        log.info("Author found with id: {}", id);
        authorMapper.toUpdateAuthor(authorRequestDto, author);
        authorRepository.save(author);
        log.info("Author with id: {} updated successfully", id);
    }

    @Override
    public void delete(Integer id) {
        log.info("Deleting author with id: {}", id);
        if (!authorRepository.existsById(id)) {
            throw new NotFoundException("Author not found with id: {} " + id);
        }
        authorRepository.deleteById(id);
        log.info("Author with id: {} deleted successfully", id);
    }

    @Override
    public List<Book> getBooksByAuthorName(String name) {
        log.info("Searching books for {} ", name);
        Author author = authorRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(("Author not found with name:" + name)));
        log.info("Author is found");
        if (author.getBooks().isEmpty()) {
            throw new NotFoundException(name + "'s books are not found");
        }
        log.info("{} 's {} books are found", name, author.getBooks().size());
        return author.getBooks();
    }
}