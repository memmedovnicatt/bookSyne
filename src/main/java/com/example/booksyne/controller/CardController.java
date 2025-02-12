package com.example.booksyne.controller;

import com.example.booksyne.model.dto.request.CardRequestDto;
import com.example.booksyne.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @Operation(summary = "Add a new card to the user account")
    @PostMapping()
    public ResponseEntity<String> addCardToUser(@RequestBody @Valid CardRequestDto cardRequestDto, HttpServletRequest httpServletRequest) {
        cardService.addCardToUser(cardRequestDto, httpServletRequest);
        return ResponseEntity.ok("Card successfully added");
    }

    @Operation(summary = "Delete a user's card by its ID")
    @DeleteMapping("/{cardId}")
    public ResponseEntity<Void> deleteCard(@PathVariable Integer cardId) {
        cardService.deleteCard(cardId);
        return ResponseEntity.ok().build();
    }
}
