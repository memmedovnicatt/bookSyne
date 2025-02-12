package com.example.booksyne.service;

import com.example.booksyne.dao.entity.User;
import com.example.booksyne.model.dto.request.FavouriteRequestDto;
import jakarta.servlet.http.HttpServletRequest;

public interface FavouriteManagerService {
    String addToFavouriteForGift(User userId, Integer productId, FavouriteRequestDto favouriteRequestDto, HttpServletRequest httpServletRequest);

    String addToFavouriteForBook(User userId,Integer productId, FavouriteRequestDto favouriteRequestDto, HttpServletRequest httpServletRequest);

    String addToFavouriteForMagazine(User userId,Integer productId, FavouriteRequestDto favouriteRequestDto, HttpServletRequest httpServletRequest);

    String addToFavouriteForBag(User userId,Integer productId, FavouriteRequestDto favouriteRequestDto, HttpServletRequest httpServletRequest);
}