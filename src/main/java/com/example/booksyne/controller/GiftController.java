package com.example.booksyne.controller;

import com.example.booksyne.dao.entity.User;
import com.example.booksyne.model.dto.request.FavouriteRequestDto;
import com.example.booksyne.model.dto.request.GiftRequestDto;
import com.example.booksyne.model.dto.request.GiftRequestForUpdateDto;
import com.example.booksyne.model.dto.response.GiftDetailByAddress;
import com.example.booksyne.model.dto.response.GiftInfoResponse;
import com.example.booksyne.model.dto.response.GiftResponseDto;
import com.example.booksyne.model.dto.response.GiftResponseForGetAll;
import com.example.booksyne.service.GiftService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gifts")
@RequiredArgsConstructor
public class GiftController {
    private final GiftService giftService;

    @Operation(summary = "Add a new Gift")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<GiftResponseDto> add(@RequestBody GiftRequestDto giftRequestDto) {
        GiftResponseDto giftResponse = giftService.add(giftRequestDto);
        return ResponseEntity.ok(giftResponse);
    }

    @Operation(summary = "Delete an author by its ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        giftService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get details of a book by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<GiftInfoResponse> getById(@PathVariable Integer id) {
        GiftInfoResponse giftInfoResponse = giftService.getById(id);
        return ResponseEntity.ok(giftInfoResponse);
    }

    @Operation(summary = "Get gifts by address")
    @GetMapping("/search/{address}")
    public ResponseEntity<List<GiftDetailByAddress>> getGiftsByAddress(@PathVariable String address) {
        List<GiftDetailByAddress> giftDetailByAddresses = giftService.getGiftsByAddress(address);
        return ResponseEntity.ok(giftDetailByAddresses);
    }

    @Operation(summary = "Update an existing gift")
    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody GiftRequestForUpdateDto giftRequestForUpdateDto, @PathVariable Integer id) {
        giftService.update(giftRequestForUpdateDto, id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Retrieve all active bags with pagination")
    @GetMapping()
    public ResponseEntity<Page<GiftResponseForGetAll>> getAll(Pageable pageable) {
        Page<GiftResponseForGetAll> giftResponseForGetAlls = giftService.getAll(pageable);
        return ResponseEntity.ok(giftResponseForGetAlls);
    }
}