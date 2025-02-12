package com.example.booksyne.service.impl;

import com.example.booksyne.dao.entity.Bag;
import com.example.booksyne.dao.entity.Favourite;
import com.example.booksyne.dao.entity.User;
import com.example.booksyne.dao.repository.BagRepository;

import com.example.booksyne.dao.repository.FavouriteRepository;
import com.example.booksyne.dao.repository.UserRepository;
import com.example.booksyne.mapper.BagMapper;
import com.example.booksyne.mapper.FavouriteMapper;
import com.example.booksyne.model.dto.request.BagRequestDto;
import com.example.booksyne.model.dto.request.FavouriteRequestDto;
import com.example.booksyne.model.dto.response.BagInfoResponse;
import com.example.booksyne.model.dto.response.BagResponse;
import com.example.booksyne.model.enums.ProductStatus;
import com.example.booksyne.model.exception.child.*;
import com.example.booksyne.service.BagService;
import com.example.booksyne.utility.FavouriteUtilityService;
import com.example.booksyne.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BagServiceImpl implements BagService {
    private final BagRepository bagRepository;
    private final BagMapper bagMapper;

    @Override
    public BagInfoResponse getById(Integer id) {
        log.info("Started the getById method with id: {} ", id);
        Bag bagResponse = bagRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Bag not found with id " + id));
        log.info("Bag found with id: {}", id);
        log.info("getById method is done");
        return bagMapper.toBagInfoResponse(bagResponse);
    }

    @Override
    public List<BagResponse> getByName(String name) {
        log.info("Started the getByName method with name: {}", name);
        List<Bag> bagResponse = bagRepository
                .findByType(name).orElseThrow(() -> new NotFoundException("Bag not found with " + name));
        if (bagResponse.isEmpty()) {
            throw new NotFoundException(name + " bag is not found");
        }
        log.info("{} book is found ", name);
        log.info("getByName method is done");
        return bagMapper.toBagListResponse(bagResponse);
    }

    @Override
    public BagResponse add(BagRequestDto bagRequestDto) {
        log.info("Adding bag with request data");
        Bag bag = bagMapper.toBag(bagRequestDto);
        bag.setStatus(ProductStatus.ACTIVE);
        bag.setStock(100);
        log.info("Bag successfully adding");
        Bag savedBagResponse = bagRepository.save(bag);
        log.info("Bag successfully saved");
        return bagMapper.toBagResponse(savedBagResponse);
    }

    @Override
    public void softDelete(Integer id, LocalDateTime localDateTime) {
        log.info("Deleting bag with id = {}", id);
        Bag bag = bagRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("This bag not found with id " + id));
        log.info("Bag is found with id: {}", id);
        if (bag.getStatus() == ProductStatus.DELETED) {
            throw new AlreadyExistsException("Bag already deleted");
        }
        bag.setDeletedAt(LocalDateTime.now());
        bag.setStatus(ProductStatus.DELETED);
        log.info("Status and date successfully adding");
        bagRepository.save(bag);
        log.info("Saved successfully");
    }

    @Override
    public void update(BagRequestDto bagRequestDto, Integer id) {
        log.info("Update method for bag with id: {} is started", id);
        Bag bag = bagRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bag not found with id " + id));
        log.info("Found bag with id: {}", id);
        bagMapper.updateEntityField(bagRequestDto, bag);
        bagRepository.save(bag);
        log.info("Bag with id: {} updated successfully", id);
    }

    @Override
    public Page<BagResponse> getAll(Pageable pageable) {
        log.info("getAll method is started");
        Page<Bag> bags = bagRepository.findByStatus(pageable, ProductStatus.ACTIVE);
        Page<BagResponse> bagResponses = bags.map(bagMapper::toBagResponse);
        log.info("getAll method is done");
        return bagResponses;
    }

    @Override
    public void activateProduct(Integer id, LocalDateTime createdAt) {
        log.info("activateProduct method is started with id= {}", id);
        Bag bag = bagRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("This bag not found with id " + id));
        log.info("Found with id: {}", id);
        if (bag.getStatus() == ProductStatus.ACTIVE) {
            throw new AlreadyExistsException("Bag already activated");
        }
        bag.setCreatedAt(LocalDateTime.now());
        bag.setStatus(ProductStatus.ACTIVE);
        log.info("Successfully adding");
        bagRepository.save(bag);
        log.info("Saved bag");
    }
}