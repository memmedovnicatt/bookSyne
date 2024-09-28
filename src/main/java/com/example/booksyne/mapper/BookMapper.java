package com.example.booksyne.mapper;

import com.example.booksyne.dao.entity.Book;
import com.example.booksyne.model.dto.request.BookRequestDto;
import com.example.booksyne.model.dto.response.BookLanguageDto;
import com.example.booksyne.model.dto.response.BookResponseDto;
import com.example.booksyne.model.dto.response.BookResponseFeature;
import com.example.booksyne.model.dto.response.BookResponseForAuthor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface BookMapper {
    @Mapping(source = "authorId", target = "author.id")
    Book toBook(BookRequestDto bookRequestDto);

    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "name", target = "name")
    BookResponseDto toBookResponse(Book book);

    void updateEntityField(BookRequestDto bookRequestDto, @MappingTarget Book book);

    List<BookLanguageDto> toBookLanguage(List<Book> books);

    List<BookResponseForAuthor> toBookForAuthor(List<Book> books);

    BookResponseFeature toBookResponseFeature(Book book);


}