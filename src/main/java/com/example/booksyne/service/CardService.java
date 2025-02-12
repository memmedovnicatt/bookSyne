package com.example.booksyne.service;

import com.example.booksyne.model.dto.request.CardRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface CardService {
    String addCardToUser(CardRequestDto cardRequestDto, HttpServletRequest httpServletRequest);

    void deleteCard(Integer cardId);
}