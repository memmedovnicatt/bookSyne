package com.example.booksyne.service;

import com.example.booksyne.dao.entity.User;
import com.example.booksyne.model.dto.request.BookRequestDto;
import com.example.booksyne.model.dto.request.FavouriteRequestDto;
import com.example.booksyne.model.dto.response.BookLanguageDto;
import com.example.booksyne.model.dto.response.BookResponseDto;
import com.example.booksyne.model.dto.response.BookResponseFeature;
import com.example.booksyne.model.dto.response.BookResponseForAuthor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface BookService {
    BookResponseDto add(BookRequestDto bookRequestDto);

    void delete(Integer id);

    void update(BookRequestDto bookRequestDto, Integer id);

    List<BookResponseForAuthor> getBooksByAuthorId(Integer authorId);

    List<BookLanguageDto> getBooksByLanguage(String language);

    BookResponseFeature getById(Integer id);
}
