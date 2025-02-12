package com.example.booksyne.service.impl;

import com.example.booksyne.dao.entity.*;
import com.example.booksyne.dao.repository.*;
import com.example.booksyne.mapper.FavouriteMapper;
import com.example.booksyne.model.dto.request.FavouriteRequestDto;
import com.example.booksyne.model.exception.child.*;
import com.example.booksyne.service.*;
import com.example.booksyne.utility.FavouriteUtilityService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FavouriteManagerServiceImpl implements FavouriteManagerService {
    private final FavouriteUtilityService favouriteUtilityService;
    private final GiftRepository giftRepository;
    private final BookRepository bookRepository;
    private final BagRepository bagRepository;
    private final MagazineRepository magazineRepository;
    private final FavouriteMapper favouriteMapper;
    private final FavouriteRepository favouriteRepository;

    @Override
    public String addToFavouriteForGift(User userId, Integer productId, FavouriteRequestDto favouriteRequestDto, HttpServletRequest httpServletRequest) {
        log.info("addToFavouriteForGift started for productId: {}", productId);
        favouriteUtilityService.checkAndAddToFavourite(productId, favouriteRequestDto, httpServletRequest);
        Gift gift = giftRepository.findById(productId).
                orElseThrow(() -> new NotFoundException("This gift not found with id " + favouriteRequestDto.getProductType()));
        Favourite favourite = favouriteMapper.toFavourite(favouriteRequestDto);
        favourite.setProductId(gift.getId());
        favourite.setUser(userId);
        favouriteRepository.save(favourite);
        log.info("Successfully added gift to favourite for user: {}", userId.getId());
        return "Successfully add to favourite";
    }

    @Override
    public String addToFavouriteForBook(User userId, Integer productId, FavouriteRequestDto favouriteRequestDto, HttpServletRequest httpServletRequest) {
        log.info("addToFavouriteForBook started for productId: {}", productId);
        favouriteUtilityService.checkAndAddToFavourite(productId, favouriteRequestDto, httpServletRequest);
        Book book = bookRepository.findById(productId).
                orElseThrow(() -> new NotFoundException("This book not  found with id " + favouriteRequestDto.getProductType()));
        Favourite favourite = favouriteMapper.toFavourite(favouriteRequestDto);
        favourite.setProductId(book.getId());
        favourite.setUser(userId);
        favouriteRepository.save(favourite);
        log.info("Successfully added book to favourite for user: {}", userId.getId());
        return "Successfully add to favourite";
    }

    @Override
    public String addToFavouriteForMagazine(User userId, Integer productId, FavouriteRequestDto favouriteRequestDto, HttpServletRequest httpServletRequest) {
        log.info("addToFavouriteForMagazine started for productId: {}", productId);
        favouriteUtilityService.checkAndAddToFavourite(productId, favouriteRequestDto, httpServletRequest);
        Magazine magazine = magazineRepository.findById(productId).
                orElseThrow(() -> new NotFoundException("This magazine not  found with id " + favouriteRequestDto.getProductType()));
        Favourite favourite = favouriteMapper.toFavourite(favouriteRequestDto);
        favourite.setProductId(magazine.getId());
        favourite.setUser(userId);
        favouriteRepository.save(favourite);
        log.info("Successfully added magazine to favourite for user: {}", userId.getId());
        return "Successfully add to favourite";
    }

    @Override
    public String addToFavouriteForBag(User userId, Integer productId, FavouriteRequestDto favouriteRequestDto, HttpServletRequest httpServletRequest) {
        log.info("addToFavouriteForBag started for productId: {}", productId);
        log.info("addToFavourite started for BAG");
        favouriteUtilityService.checkAndAddToFavourite(productId, favouriteRequestDto, httpServletRequest);
        Bag bag = bagRepository.findById(productId).
                orElseThrow(() -> new NotFoundException("This bag not  found with id " + favouriteRequestDto.getProductType()));
        Favourite favourite = favouriteMapper.toFavourite(favouriteRequestDto);
        favourite.setProductId(bag.getId());
        favourite.setUser(userId);
        favouriteRepository.save(favourite);
        log.info("Successfully added bag to favourite for user: {}", userId.getId());
        return "Successfully add to favourite";
    }
}