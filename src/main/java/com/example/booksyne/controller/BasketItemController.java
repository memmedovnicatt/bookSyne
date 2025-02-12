package com.example.booksyne.controller;

import com.example.booksyne.dao.repository.BasketRepository;
import com.example.booksyne.model.dto.request.BasketItemRequestDto;

import com.example.booksyne.model.dto.response.BasketItemResponse;
import com.example.booksyne.service.BasketItemService;
import com.example.booksyne.service.BasketService;
import com.example.booksyne.service.JwtService;
import com.example.booksyne.service.impl.BasketServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/basket-items")
public class BasketItemController {
    private final BasketItemService basketItemService;
    private final JwtService jwtService;


    @Operation(summary = "Add product to basket")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ResponseEntity<Void> add(@RequestBody BasketItemRequestDto basketItemRequestDto, HttpServletRequest httpServletRequest) {
        basketItemService.addProductToBasket(basketItemRequestDto, httpServletRequest);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Remove an item from the user's basket")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{basketItemId}")
    public ResponseEntity<Void> delete(@PathVariable Integer basketItemId) {
        basketItemService.deleteProduct(basketItemId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Retrieve all items in the user's basket")
    @GetMapping()
    public ResponseEntity<List<BasketItemResponse>> getAll(HttpServletRequest httpServletRequest) {
        Integer userId = jwtService.getUserId(jwtService.resolveClaims(httpServletRequest));
        List<BasketItemResponse> basketItemResponses = basketItemService.getProducts(userId);
        return ResponseEntity.ok(basketItemResponses);
    }
}