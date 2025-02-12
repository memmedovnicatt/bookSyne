package com.example.booksyne.service.impl;
import com.example.booksyne.dao.entity.*;
import com.example.booksyne.dao.repository.*;
import com.example.booksyne.mapper.BasketItemMapper;
import com.example.booksyne.model.dto.request.BasketItemRequestDto;
import com.example.booksyne.model.dto.response.BasketItemResponse;
import com.example.booksyne.model.enums.BasketItemStatus;
import com.example.booksyne.model.enums.ProductType;
import com.example.booksyne.model.exception.child.*;
import com.example.booksyne.service.BasketItemService;
import com.example.booksyne.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class BasketItemServiceImpl implements BasketItemService {
    private final BasketItemMapper basketItemMapper;
    private final BasketItemRepository basketItemRepository;
    private final JwtService jwtService;
    private final BagRepository bagRepository;
    private final UserRepository userRepository;
    private final BasketRepository basketRepository;
    private final BookRepository bookRepository;
    private final GiftRepository giftRepository;
    private final MagazineRepository magazineRepository;

    @Transactional
    @Override
    public void addProductToBasket(BasketItemRequestDto basketItemRequestDto, HttpServletRequest httpServletRequest) {
        log.info("addProductToBasket method was started");
        Integer userId = jwtService.getUserId(jwtService.resolveClaims(httpServletRequest));
        User user = userRepository
                .findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        log.info("User found");
        if (basketItemRequestDto.getProductType() == ProductType.BAG) {
            Bag bag = bagRepository.findById(basketItemRequestDto.getProductId())
                    .orElseThrow(() -> new NotFoundException("This bag not found with id " + basketItemRequestDto.getProductId()));
            fillBasketAndSave(basketItemRequestDto, userId, bag.getPrice(), bag.getCurrency());
        }
        if (basketItemRequestDto.getProductType() == ProductType.BOOK) {
            Book book = bookRepository.findById(basketItemRequestDto.getProductId())
                    .orElseThrow(() -> new NotFoundException("This book not found with id " + basketItemRequestDto.getProductId()));
            fillBasketAndSave(basketItemRequestDto, userId, book.getPrice(), book.getCurrency());
        }
        if (basketItemRequestDto.getProductType() == ProductType.MAGAZINE) {
            Magazine magazine = magazineRepository.findById(basketItemRequestDto.getProductId())
                    .orElseThrow(() -> new NotFoundException("This magazine not found with id " + basketItemRequestDto.getProductId()));
            fillBasketAndSave(basketItemRequestDto, userId, magazine.getPrice(), magazine.getCurrency());
        }
        if (basketItemRequestDto.getProductType() == ProductType.GIFT) {
            Gift gift = giftRepository.findById(basketItemRequestDto.getProductId())
                    .orElseThrow(() -> new NotFoundException("This gift not found with id " + basketItemRequestDto.getProductId()));
            fillBasketAndSave(basketItemRequestDto, userId, gift.getPrice(), gift.getCurrency());
        }
    }

    public void fillBasketAndSave(BasketItemRequestDto basketItemRequestDto, Integer userId, Double price, String currency) {
        log.info("fillBasketAndSave method was started");

        Basket basket = basketRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("This basket now found with user id " + userId));

        BasketItem existItem = basketItemRepository.findByBasketIdAndProductId(basket.getId(), basketItemRequestDto.getProductId());
        if (existItem != null) {
            existItem.setQuantity(existItem.getQuantity() + basketItemRequestDto.getQuantity());
            existItem.setPrice(price * existItem.getQuantity());
            existItem.setCurrency(currency);
        } else {
            existItem = basketItemMapper.toBasketItem(basketItemRequestDto);
            existItem.setCurrency(currency);
            existItem.setPrice(price * basketItemRequestDto.getQuantity());
            existItem.setBasket(basket);
        }
        basketItemRepository.save(existItem);
        log.info("Method was done");
    }

    @Override
    public List<BasketItemResponse> getProducts(Integer userId) {
        log.info("getProducts method was started for basketItem");
        Basket basket = basketRepository
                .findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("THis basket not found with this user id " + userId));
        List<BasketItem> basketItemList = basketItemRepository.findByBasketId(basket.getId());
        List<BasketItemResponse> itemResponses = new ArrayList<>();
        if (basketItemList.isEmpty()) {
            throw new NotFoundException("BasketItem not found");
        }
        for (BasketItem basketItem : basketItemList) {
            BasketItemResponse basketItemResponse = new BasketItemResponse();
            basketItemResponse.setProductId(basketItem.getProductId());
            basketItemResponse.setProductType(basketItem.getProductType());
            boolean isProcessed = false;
            if (basketItem.getProductType() == ProductType.BAG) {
                Bag bag = bagRepository.findById(basketItem.getProductId())
                        .orElseThrow(() -> new NotFoundException("This bag not found with id:" + basketItem.getProductId()));
                basketItemResponse.setCurrency(bag.getCurrency());
                basketItemResponse.setPrice(bag.getPrice());
                basketItemResponse.setType(bag.getType());
                basketItemResponse.setQuantity(basketItem.getQuantity());
                isProcessed = true;
            }
            if (basketItem.getProductType() == ProductType.BOOK) {
                Book book = bookRepository.findById(basketItem.getProductId())
                        .orElseThrow(() -> new NotFoundException("This book not found with id:" + basketItem.getProductId()));
                basketItemResponse.setCurrency(book.getCurrency());
                basketItemResponse.setPrice(book.getPrice());
                basketItemResponse.setType(book.getName());
                basketItemResponse.setQuantity(basketItem.getQuantity());
                isProcessed = true;
            }
            if (basketItem.getProductType() == ProductType.GIFT) {
                Gift gift = giftRepository.findById(basketItem.getProductId())
                        .orElseThrow(() -> new NotFoundException("This gift not found with id:" + basketItem.getProductId()));
                basketItemResponse.setCurrency(gift.getCurrency());
                basketItemResponse.setPrice(gift.getPrice());
                basketItemResponse.setType(gift.getName());
                basketItemResponse.setQuantity(basketItem.getQuantity());
                isProcessed = true;
            }
            if (basketItem.getProductType() == ProductType.MAGAZINE) {
                Magazine magazine = magazineRepository.findById(basketItem.getProductId())
                        .orElseThrow(() -> new NotFoundException("This magazine not found with id:" + basketItem.getProductId()));
                basketItemResponse.setCurrency(magazine.getCurrency());
                basketItemResponse.setPrice(magazine.getPrice());
                basketItemResponse.setType(magazine.getEditionName());
                basketItemResponse.setQuantity(basketItem.getQuantity());
                isProcessed = true;
            }
            if (isProcessed) {
                itemResponses.add(basketItemResponse);
            }
        }
        return itemResponses;
    }

    @Override
    public void deleteProduct(Integer basketItemId) {
        basketItemRepository.deleteById(basketItemId);
    }
}