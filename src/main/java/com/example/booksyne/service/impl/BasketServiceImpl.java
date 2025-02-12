package com.example.booksyne.service.impl;

import com.example.booksyne.dao.entity.Basket;
import com.example.booksyne.dao.entity.BasketItem;
import com.example.booksyne.dao.entity.User;
import com.example.booksyne.dao.repository.BasketItemRepository;
import com.example.booksyne.dao.repository.BasketRepository;
import com.example.booksyne.dao.repository.UserRepository;
import com.example.booksyne.mapper.BasketItemMapper;
import com.example.booksyne.mapper.BasketMapper;
import com.example.booksyne.model.dto.request.BasketRequestDto;

import com.example.booksyne.model.enums.BasketStatus;
import com.example.booksyne.model.exception.child.*;
import com.example.booksyne.service.BagService;
import com.example.booksyne.service.BasketService;
import com.example.booksyne.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BasketServiceImpl implements BasketService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final BasketRepository basketRepository;
    private final BasketMapper basketMapper;
    private final BasketItemRepository basketItemRepository;


    @Override
    public void createBasket(BasketRequestDto basketRequestDto, HttpServletRequest httpServletRequest) {
        log.info("Create basket method was started for user");
        Integer userId = jwtService.getUserId(jwtService.resolveClaims(httpServletRequest));
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        if (basketRepository.existsByUserId(userId)) {
            throw new AlreadyExistsException("The user with id " + userId + " already has a basket");
        }
        log.info("Basket created for userId:{}", userId);
        Basket basket = basketMapper.toBasket(basketRequestDto);
        basket.setUser(user);
        log.info("Successfully created");
        basketRepository.save(basket);
        log.info("Successfully saved");
    }

    @Transactional
    @Override
    public void deleteBasket(HttpServletRequest httpServletRequest) {
        log.info("Delete basket method was started");
        Integer userId = jwtService.getUserId(jwtService.resolveClaims(httpServletRequest));
        Basket basket = basketRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("This basket not found for userId:" + userId));
        log.info("Basket was found");
        List<BasketItem> basketItem = basketItemRepository
                .deleteByBasketId(basket.getId());
        if (!basketItem.isEmpty()) {
            log.info("BasketItem was found");

            basketItemRepository.deleteByBasketId(basket.getId());
            log.info("Basket items deleted");
            basket.setBasketStatus(BasketStatus.INACTIVE);
            log.info("Basket status to be updated: {}", basket.getBasketStatus());
            basketRepository.save(basket);
            log.info("Basket status updated to INACTIVE");
        }
        basket.setBasketStatus(BasketStatus.INACTIVE);
        basketRepository.save(basket);
    }

    @Override
    public void activatedBasket(HttpServletRequest httpServletRequest) {
        log.info("activatedBasket method was started");
        Integer userId = jwtService.getUserId(jwtService.resolveClaims(httpServletRequest));
        Basket basket = basketRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("This basket not found for userId:" + userId));
        log.info("Inactive basket was found");
        basket.setBasketStatus(BasketStatus.ACTIVE);
        basketRepository.save(basket);
    }

    @Override
    public BasketStatus checkStatusForUser(HttpServletRequest httpServletRequest) {
        log.info("checkStatusForUser method was started");
        Integer userId = jwtService.getUserId(jwtService.resolveClaims(httpServletRequest));
        Basket basket = basketRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("This basket not found for userId:" + userId));
        log.info("Your basket status is {}", basket.getBasketStatus());
        return basket.getBasketStatus();
    }
}