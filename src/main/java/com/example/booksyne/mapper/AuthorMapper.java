package com.example.booksyne.mapper;

import com.example.booksyne.dao.entity.Author;
import com.example.booksyne.dao.entity.Bag;
import com.example.booksyne.dao.entity.Book;
import com.example.booksyne.model.dto.request.AuthorRequestDto;
import com.example.booksyne.model.dto.request.BagRequestDto;
import com.example.booksyne.model.dto.response.AuthorResponse;
import com.example.booksyne.model.dto.response.AuthorResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthorMapper {
    @Mapping(source = "authorName", target = "name")
    @Mapping(source = "authorNationality", target = "nationality")
    Author toAuthor(AuthorRequestDto authorRequestDto);

    AuthorResponseDto toAuthorResponse(Author author);

    @Mapping(source = "authorName", target = "name")
    @Mapping(source = "authorNationality", target = "nationality")
    void toUpdateAuthor(AuthorRequestDto authorRequestDto, @MappingTarget Author author);

    AuthorResponse toAuthorName(Author author);

}
