package com.example.booksyne.service;

import com.example.booksyne.model.dto.request.DeleteFavouriteRequest;
import com.example.booksyne.model.dto.request.FavouriteRequestDto;
import com.example.booksyne.model.dto.response.FavouriteDetailDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FavouriteService {
    void add(Integer productId, FavouriteRequestDto favouriteRequestDto, HttpServletRequest httpServletRequest);

    String delete(DeleteFavouriteRequest deleteFavouriteRequest, HttpServletRequest httpServletRequest);

    List<FavouriteDetailDto> getFavouriteDetails(HttpServletRequest httpServletRequest);
}