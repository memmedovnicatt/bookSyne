package com.example.booksyne.service.impl;

import com.example.booksyne.dao.entity.Card;
import com.example.booksyne.dao.entity.User;
import com.example.booksyne.dao.repository.CardRepository;
import com.example.booksyne.dao.repository.UserRepository;
import com.example.booksyne.mapper.CardMapper;
import com.example.booksyne.model.dto.request.CardRequestDto;
import com.example.booksyne.model.enums.CardStatus;
import com.example.booksyne.model.exception.child.AlreadyExistsException;
import com.example.booksyne.model.exception.child.NotFoundException;
import com.example.booksyne.service.CardService;
import com.example.booksyne.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j

public class CardServiceImpl implements CardService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final CardMapper cardMapper;
    private final CardRepository cardRepository;

    @Override
    public String addCardToUser(CardRequestDto cardRequestDto, HttpServletRequest httpServletRequest) {
        log.info("addCardToUser method was started");
        Integer userId = jwtService.getUserId(jwtService.resolveClaims(httpServletRequest));
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        log.info("User Found");
        Card card = cardMapper.toCard(cardRequestDto);
        Card existingCard = cardRepository.findByCardNumber(card.getCardNumber());
        if (existingCard != null) {
            throw new AlreadyExistsException("This card already exists");
        }
        card.setCardStatus(CardStatus.ACTIVE);
        card.setUser(user);
        cardRepository.save(card);
        return "Card successfully saved";
    }

    @Override
    public void deleteCard(Integer cardId) {
        log.info("deleteCard method was started");
        if (!cardRepository.existsById(cardId)) {
            throw new NotFoundException("This card not found");
        }
        log.info("Card was found");
        cardRepository.deleteById(cardId);
        log.info("Card with id: {} deleted successfully", cardId);
    }
}