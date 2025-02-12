package com.example.booksyne.service;

import com.example.booksyne.model.dto.request.BasketRequestDto;

import com.example.booksyne.model.enums.BasketStatus;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BasketService {
    void createBasket(BasketRequestDto basketRequestDto, HttpServletRequest httpServletRequest);

    void deleteBasket(HttpServletRequest httpServletRequest);

    void activatedBasket(HttpServletRequest httpServletRequest);

    BasketStatus checkStatusForUser(HttpServletRequest httpServletRequest);
}
