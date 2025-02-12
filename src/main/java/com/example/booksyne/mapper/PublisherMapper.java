package com.example.booksyne.mapper;

import com.example.booksyne.dao.entity.Publisher;
import com.example.booksyne.model.dto.request.PublisherRequestDto;
import com.example.booksyne.model.dto.request.PublisherUpdateDto;
import com.example.booksyne.model.dto.response.PublisherResponse;
import com.example.booksyne.model.dto.response.PublisherResponseDetail;
import com.example.booksyne.model.dto.response.PublisherResponseName;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PublisherMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "address", target = "address")
    Publisher toPublisher(PublisherRequestDto publisherRequestDto);

    PublisherResponse toPublisherResponse(Publisher publisher);

    List<PublisherResponseName> toPublisherResponseDetail(List<Publisher> publisher);

    PublisherResponseDetail toPublisherResponseDetail(Publisher publisher);

    void updateEntityField(PublisherUpdateDto publisherUpdateDto, @MappingTarget Publisher publisher);
}
