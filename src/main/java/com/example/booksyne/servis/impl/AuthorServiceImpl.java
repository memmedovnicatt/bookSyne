package com.example.booksyne.servis.impl;

import com.example.booksyne.dao.entity.Author;
import com.example.booksyne.dao.entity.Book;
import com.example.booksyne.dao.repository.AuthorRepository;
import com.example.booksyne.mapper.AuthorMapper;
import com.example.booksyne.model.dto.request.AuthorRequestDto;
import com.example.booksyne.model.dto.response.AuthorResponse;
import com.example.booksyne.model.dto.response.AuthorResponseDto;
import com.example.booksyne.model.exception.child.AuthorNotFoundException;
import com.example.booksyne.model.exception.child.BagNotFoundException;
import com.example.booksyne.servis.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public AuthorResponseDto add(AuthorRequestDto authorRequestDto) {
        Author author = authorMapper.toAuthor(authorRequestDto);
        Author savedAuthor = authorRepository.save(author);
        return authorMapper.toAuthorResponse(savedAuthor);
    }

    @Override
    public void update(AuthorRequestDto authorRequestDto, Integer id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Author not found with id " + id));
        authorMapper.toUpdateAuthor(authorRequestDto, author);
        authorRepository.save(author);
    }

    @Override
    public void delete(Integer id) {
        authorRepository.deleteById(id);
    }

    @Override
    public List<Book> getBooks(String name) {
        Author author = authorRepository.findByName(name)
                .orElseThrow(() -> new AuthorNotFoundException(("Author not found with name " + name)));
        if (author != null) {
            return author.getBooks();
        } else {
            return Collections.emptyList();
        }
    }
}
