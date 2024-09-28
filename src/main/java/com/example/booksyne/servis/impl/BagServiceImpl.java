package com.example.booksyne.servis.impl;

import com.example.booksyne.dao.entity.Bag;
import com.example.booksyne.dao.repository.BagRepository;
import com.example.booksyne.mapper.BagMapper;
import com.example.booksyne.model.dto.request.BagRequestDto;
import com.example.booksyne.model.dto.response.BagInfoResponse;
import com.example.booksyne.model.dto.response.BagResponse;
import com.example.booksyne.model.exception.child.BagNotFoundException;
import com.example.booksyne.servis.BagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BagServiceImpl implements BagService {
    private final BagRepository bagRepository;
    private final BagMapper bagMapper;


    @Override
    public BagInfoResponse getById(Integer id) {
        log.info("Started the getById method with id = " + id);
        Bag bagResponse = bagRepository.findById(id).orElseThrow(() ->
                new BagNotFoundException("Bag not found with id " + id));
        log.info("getById method is done");
        return bagMapper.toBagInfoResponse(bagResponse);
    }

    @Override
    public BagResponse getByName(String name) {
        log.info("Started the getByName method with name =" + name);
        Bag bagResponse = bagRepository.findByType(name).orElseThrow(() -> new BagNotFoundException("Bag with " + name));
        log.info("getByName method is done");
        return bagMapper.toBagResponse(bagResponse);
    }

    @Override
    public BagResponse add(BagRequestDto bagRequestDto) {
        Bag bag = bagMapper.toBag(bagRequestDto);
        Bag savedBagResponse = bagRepository.save(bag);
        return bagMapper.toBagResponse(savedBagResponse);
    }

    @Override
    public void delete(Integer id) {
        bagRepository.deleteById(id);
    }

    @Override
    public void update(BagRequestDto bagRequestDto,Integer id) {
        Bag bag=bagRepository.findById(id).orElseThrow(()->new BagNotFoundException("Bag not found with id "+id));
        bagMapper.updateEntityField(bagRequestDto,bag);
        bagRepository.save(bag);
    }

    @Override
    public Page<BagResponse> getAll(Pageable pageable) {
        log.info("getAll method is started");
        Page<Bag> bagPage = bagRepository.findAll(pageable);
        Page<BagResponse> bagInfoPage = bagPage.map(bagMapper::toBagResponse);
        log.info("getAll method is done");
        return bagInfoPage;

    }
}
