package com.example.booksyne.servis;

import com.example.booksyne.dao.entity.Author;
import com.example.booksyne.dao.entity.Book;
import com.example.booksyne.model.dto.request.AuthorRequestDto;
import com.example.booksyne.model.dto.request.BagRequestDto;
import com.example.booksyne.model.dto.response.AuthorResponse;
import com.example.booksyne.model.dto.response.AuthorResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    AuthorResponseDto add(AuthorRequestDto authorRequestDto);

    void update(AuthorRequestDto authorRequestDto,Integer id);

    void delete(Integer id);



    List<Book> getBooks(String name);



}
