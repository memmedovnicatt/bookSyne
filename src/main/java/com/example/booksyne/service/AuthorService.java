package com.example.booksyne.service;

import com.example.booksyne.dao.entity.Book;
import com.example.booksyne.model.dto.request.AuthorRequestDto;
import com.example.booksyne.model.dto.response.AuthorResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    AuthorResponseDto add(AuthorRequestDto authorRequestDto);

    void update(AuthorRequestDto authorRequestDto, Integer id);

    void delete(Integer id);

    List<Book> getBooksByAuthorName(String name);


}
