package com.example.booksyne.service;

import com.example.booksyne.model.dto.request.BasketItemRequestDto;

import com.example.booksyne.model.dto.response.BasketItemResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BasketItemService {
    void addProductToBasket(BasketItemRequestDto basketItemRequestDto, HttpServletRequest httpServletRequest);

    void deleteProduct(Integer basketItemId);

    List<BasketItemResponse> getProducts(Integer userId);
}