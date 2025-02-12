package com.example.booksyne.service.impl;

import com.example.booksyne.dao.entity.*;
import com.example.booksyne.dao.repository.GiftRepository;
import com.example.booksyne.dao.repository.SupplierRepository;
import com.example.booksyne.mapper.GiftMapper;
import com.example.booksyne.model.dto.request.GiftRequestDto;
import com.example.booksyne.model.dto.request.GiftRequestForUpdateDto;
import com.example.booksyne.model.dto.response.*;
import com.example.booksyne.model.exception.child.NotFoundException;
import com.example.booksyne.service.GiftService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GiftServiceImpl implements GiftService {
    private final GiftRepository giftRepository;
    private final GiftMapper giftMapper;
    private final SupplierRepository supplierRepository;

    @Override
    public GiftResponseDto add(GiftRequestDto giftRequestDto) {
        log.info("Adding gift with request data: {}", giftRequestDto);
        Supplier supplier = supplierRepository
                .findById(giftRequestDto.getSupplierId())
                .orElseThrow(() -> new NotFoundException("This supplier not found with id:" + giftRequestDto.getSupplierId()));
        log.info("Supplier is found with id:{}", giftRequestDto.getSupplierId());
        Gift gift = giftMapper.toGift(giftRequestDto);
        gift.setStock(100);
        log.info("Gift successfully adding");
        gift.setSupplier(supplier);
        Gift savedGift = giftRepository.save(gift);
        log.info("Gift saved successfully");
        return giftMapper.toGiftResponseDto(savedGift);
    }

    @Override
    public void delete(Integer id) {
        log.info("Deleting method starting with id: {}", id);
        if (!giftRepository.existsById(id)) {
            throw new NotFoundException("This gift not found with id:" + id);
        }
        log.info("Gift is found with id: {}", id);
        giftRepository.deleteById(id);
        log.info("Gift is successfully with id: {}", id);
    }

    @Override
    public GiftInfoResponse getById(Integer id) {
        Gift gift = giftRepository.
                findById(id).orElseThrow(() -> new NotFoundException("This gift not found with id:" + id));
        log.info("Gift is found");
        return giftMapper.toGiftInfoResponse(gift);
    }

    @Override
    public List<GiftDetailByAddress> getGiftsByAddress(String address) {
        Supplier supplier = supplierRepository.findByAddress(address)
                .orElseThrow(() -> new NotFoundException(address + " address not found for gift"));
        log.info("Address is found");
        if (supplier.getGifts().isEmpty()) {
            throw new NotFoundException("This gift not found for this address: " + address);
        }
        log.info("Gift found for this address: {}", address);
        return giftMapper.toGiftDetail(supplier.getGifts());
    }

    @Override
    public void update(GiftRequestForUpdateDto giftRequestForUpdateDto, Integer id) {
        log.info("Update method for gift with id: {} is started", id);
        Gift gift = giftRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("This gift not found with id:" + id));
        log.info("Gift found");
        Supplier supplier = supplierRepository.findById(giftRequestForUpdateDto.getSupplierId())
                .orElseThrow(()
                        -> new NotFoundException
                        ("This supplier not found with id:" + giftRequestForUpdateDto.getSupplierId()));
        log.info("Supplier found");
        gift.setSupplier(supplier);
        giftMapper.updateEntityField(giftRequestForUpdateDto, gift);
        giftRepository.save(gift);
        log.info("Gift successfully saved");
        log.info("Gift with id: {} updated successfully", id);
    }

    @Override
    public Page<GiftResponseForGetAll> getAll(Pageable pageable) {
        log.info("Method getAll is started with pagination");
        Page<Gift> giftPage = giftRepository.findAll(pageable);
        log.info("getAll method is done");
        return giftPage.map(giftMapper::toGiftResponseForGetAll);
    }
}