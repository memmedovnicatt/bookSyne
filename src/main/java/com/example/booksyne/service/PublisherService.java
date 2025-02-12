package com.example.booksyne.service;

import com.example.booksyne.dao.entity.Publisher;
import com.example.booksyne.model.dto.request.PublisherRequestDto;
import com.example.booksyne.model.dto.request.PublisherUpdateDto;
import com.example.booksyne.model.dto.response.PublisherResponse;
import com.example.booksyne.model.dto.response.PublisherResponseDetail;
import com.example.booksyne.model.dto.response.PublisherResponseName;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Service
public interface PublisherService {
    PublisherResponse add(PublisherRequestDto publisherRequestDto);

    List<PublisherResponseName> getPublisherNames();

    void delete(Integer id);

    PublisherResponseDetail getById(Integer id);

    void update(Integer id, PublisherUpdateDto publisherUpdateDto);
}
