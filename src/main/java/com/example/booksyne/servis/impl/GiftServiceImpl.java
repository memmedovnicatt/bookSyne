package com.example.booksyne.servis.impl;

import com.example.booksyne.dao.entity.Gift;
import com.example.booksyne.dao.repository.GiftRepository;
import com.example.booksyne.servis.GiftService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GiftServiceImpl implements GiftService {
    private final GiftRepository giftRepository;
    @Override
    public Gift add(Gift gift) {
        return giftRepository.save(gift);
    }
}
