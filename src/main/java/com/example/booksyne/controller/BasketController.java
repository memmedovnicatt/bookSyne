package com.example.booksyne.controller;

import com.example.booksyne.dao.entity.BasketItem;
import com.example.booksyne.model.dto.request.BasketRequestDto;

import com.example.booksyne.model.enums.BasketStatus;
import com.example.booksyne.service.BasketService;
import com.example.booksyne.service.JwtService;
import com.example.booksyne.service.impl.BasketServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("baskets")
@RestController
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;

    @Operation(summary = "Create a new basket for the user")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> create(@RequestBody BasketRequestDto basketRequestDto, HttpServletRequest httpServletRequest) {
        basketService.createBasket(basketRequestDto, httpServletRequest);
        return ResponseEntity.ok("Basket created successfully");
    }

    @Operation(summary = "Delete a basket for user")
    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(HttpServletRequest httpServletRequest) {
        basketService.deleteBasket(httpServletRequest);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Activate the current user's basket")
    @PatchMapping()
    public ResponseEntity<Void> activatedBasket(HttpServletRequest httpServletRequest) {
        basketService.activatedBasket(httpServletRequest);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Check the current status of the user's basket")
    @GetMapping()
    public ResponseEntity<BasketStatus> checkStatusForUser(HttpServletRequest httpServletRequest) {
        BasketStatus basketStatus = basketService.checkStatusForUser(httpServletRequest);
        return ResponseEntity.ok(basketStatus);
    }
}