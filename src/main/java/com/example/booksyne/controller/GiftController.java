package com.example.booksyne.controller;

import com.example.booksyne.dao.entity.Gift;
import com.example.booksyne.servis.GiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gifts")
@RequiredArgsConstructor
public class GiftController {
    private final GiftService giftService;

    @PostMapping
    public Gift addGift(@RequestBody Gift gift) {
        return giftService.add(gift);
    }
}