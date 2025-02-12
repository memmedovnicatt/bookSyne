package com.example.booksyne.service.impl;
import com.example.booksyne.dao.entity.*;
import com.example.booksyne.dao.repository.MagazineRepository;
import com.example.booksyne.dao.repository.PublisherRepository;
import com.example.booksyne.mapper.MagazineMapper;
import com.example.booksyne.model.dto.request.MagazineDtoForUpdate;
import com.example.booksyne.model.dto.request.MagazineRequestDto;
import com.example.booksyne.model.dto.response.MagazineInfoResponse;
import com.example.booksyne.model.dto.response.MagazineResponse;
import com.example.booksyne.model.dto.response.MagazineResponseForGetAll;
import com.example.booksyne.model.enums.ProductStatus;
import com.example.booksyne.model.exception.child.*;
import com.example.booksyne.service.MagazineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
@Slf4j
public class MagazineServiceImpl implements MagazineService {
    private final MagazineRepository magazineRepository;
    private final PublisherRepository publisherRepository;
    private final MagazineMapper magazineMapper;

    @Override
    public MagazineResponse add(MagazineRequestDto magazineRequest) {
        log.info("Started add method");
        Publisher publisher = publisherRepository.
                findById(magazineRequest.getPublisherId()).orElseThrow(() -> new NotFoundException("This publisher not found with id : " + magazineRequest.getPublisherId()));
        log.info("Publisher found with id:{}", magazineRequest.getPublisherId());
        Magazine magazine = magazineMapper.toMagazine(magazineRequest);
        magazine.setStatus(ProductStatus.ACTIVE);
        magazine.setStock(100);
        log.info("Successfully add");
        Magazine savedMagazine = magazineRepository.save(magazine);
        magazine.setPublisher(publisher);
        log.info("Successfully saved");
        return magazineMapper.toMagazineResponse(savedMagazine);
    }

    @Override
    public void softDelete(Integer id, LocalDateTime localDateTime) {
        log.info("Soft delete is starting with {} id and this {} in time", id, localDateTime);
        Magazine magazine = magazineRepository.
                findById(id).orElseThrow(() -> new NotFoundException("This magazine not found with id " + id));
        log.info("Magazine found");
        if (magazine.getStatus() == ProductStatus.DELETED) {
            throw new AlreadyExistsException("This magazine already on deleted status with id:" + id);
        }
        magazine.setDeletedAt(LocalDateTime.now());
        magazine.setStatus(ProductStatus.DELETED);
        log.info("Successfully adding time and status");
        magazineRepository.save(magazine);
        log.info("Successfully saved with status and time");
    }

    @Override
    public void activateProduct(Integer id, LocalDateTime createdAt) {
        log.info("Activate product method is starting with {} id and this {} in time", id, createdAt);
        Magazine magazine = magazineRepository.
                findById(id).orElseThrow(() -> new NotFoundException("This magazine not found with id " + id));
        log.info("Found");
        if (magazine.getStatus() == ProductStatus.ACTIVE) {
            throw new AlreadyExistsException("This magazine already activated with id:" + id);
        }
        magazine.setCreatedAt(LocalDateTime.now());
        magazine.setStatus(ProductStatus.ACTIVE);
        log.info("Successful adding");
        magazineRepository.save(magazine);
        log.info("Saved");
    }

    @Override
    public MagazineInfoResponse getById(Integer id) {
        log.info("getById method is starting with id {}", id);
        Magazine magazine = magazineRepository
                .findById(id).orElseThrow(() -> new NotFoundException("This magazine not found with id " + id));
        log.info("Founded with id:{}", id);
        return magazineMapper.toMagazineInfoResponse(magazine);
    }

    @Override
    public Page<MagazineResponseForGetAll> getAll(Pageable pageable) {
        log.info("getAll method was started");
        Page<Magazine> magazinePage = magazineRepository.findByStatus(pageable, ProductStatus.ACTIVE);
        log.info("Method was done");
        return magazinePage.map(magazineMapper::toMagazineResponseGetAll);
    }

    @Override
    public void update(MagazineDtoForUpdate magazineDtoForUpdate, Integer id) {
        log.info("Partial update started with id: {}", id);
        Magazine magazine = magazineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("This magazine not found with id:" + id));
        log.info("Magazine found.");
        Publisher publisher = publisherRepository
                .findById(magazineDtoForUpdate.getPublisherId())
                .orElseThrow(() -> new NotFoundException("This publisher not found with id:" + magazineDtoForUpdate.getPublisherId()));
        log.info("publisher found with id:{}", magazineDtoForUpdate.getPublisherId());
        magazine.setPublisher(publisher);
        magazineMapper.updateEntityField(magazineDtoForUpdate, magazine);
        log.info("Successfully mapping for magazine update method");
        magazineRepository.save(magazine);
        log.info("Successfully response:{}", magazine);
    }
}