package com.example.booksyne.service.impl;

import com.example.booksyne.dao.entity.Publisher;
import com.example.booksyne.dao.repository.PublisherRepository;
import com.example.booksyne.mapper.PublisherMapper;
import com.example.booksyne.model.dto.request.PublisherRequestDto;
import com.example.booksyne.model.dto.request.PublisherUpdateDto;
import com.example.booksyne.model.dto.response.PublisherResponse;
import com.example.booksyne.model.dto.response.PublisherResponseDetail;
import com.example.booksyne.model.dto.response.PublisherResponseName;
import com.example.booksyne.model.exception.child.NotFoundException;
import com.example.booksyne.service.PublisherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;


    @Override
    public PublisherResponse add(PublisherRequestDto publisherRequestDto) {
        log.info("Adding method started");
        Publisher publisher = publisherMapper.toPublisher(publisherRequestDto);
        log.info("Successfully adding from request to Publisher");
        Publisher savedPublisher = publisherRepository.save(publisher);
        log.info("Saved");
        return publisherMapper.toPublisherResponse(savedPublisher);
    }

    @Transactional
    @Override
    public List<PublisherResponseName> getPublisherNames() {
        log.info("getPublisherNames method was started");
        List<Publisher> publisher = publisherRepository.findAll();
        log.info("Fetched publishers: {}", publisher.size());
        log.info("Method done");
        return publisherMapper.toPublisherResponseDetail(publisher);
    }

    @Override
    public void delete(Integer id) {
        log.info("Deleting method was started with id:{}", id);
        if (!publisherRepository.existsById(id)) {
            throw new NotFoundException("This publisher not found with id:" + id);
        }
        log.info("Found publisher with id:{}", id);
        publisherRepository.deleteById(id);
        log.info("Successfully deleted id {} form table", id);
    }

    @Override
    public PublisherResponseDetail getById(Integer id) {
        log.info("getById method was started with id:{}", id);
        Publisher publisher = publisherRepository
                .findById(id)
                .orElseThrow(()
                        -> new NotFoundException("This publisher not found with id " + id));
        log.info("Found with id:{}", id);
        return publisherMapper.toPublisherResponseDetail(publisher);
    }

    @Override
    public void update(Integer id, PublisherUpdateDto publisherUpdateDto) {
        log.info("Update method was started with id {} and this request: {}", id, publisherUpdateDto);
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("This publisher not found with id:" + id));
        log.info("Publisher found");
        publisherMapper.updateEntityField(publisherUpdateDto, publisher);
        log.info("Update successfully");
        publisherRepository.save(publisher);
        log.info("Successfully saved");
    }
}
