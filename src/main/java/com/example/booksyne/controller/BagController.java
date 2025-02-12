package com.example.booksyne.controller;

import com.example.booksyne.dao.entity.User;
import com.example.booksyne.model.dto.request.BagRequestDto;
import com.example.booksyne.model.dto.request.FavouriteRequestDto;
import com.example.booksyne.model.dto.response.BagInfoResponse;
import com.example.booksyne.model.dto.response.BagResponse;
import com.example.booksyne.service.BagService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/bags")
@RequiredArgsConstructor
public class BagController {
    private final BagService bagService;

    @Operation(summary = "Retrieve bag details by ID")
    @GetMapping("/{id}")
    public ResponseEntity<BagInfoResponse> getById(@PathVariable Integer id) {
        BagInfoResponse bagResponse = bagService.getById(id);
        return ResponseEntity.ok(bagResponse);
    }

    @Operation(summary = "Search for bags by name")
    @GetMapping("/search")
    public ResponseEntity<List<BagResponse>> getByName(@RequestParam String name) {
        List<BagResponse> bagResponse = bagService.getByName(name);
        return ResponseEntity.ok(bagResponse);
    }

    @Operation(summary = "Retrieve all active bags with pagination")
    @GetMapping
    public ResponseEntity<Page<BagResponse>> getAll(Pageable pageable) {
        Page<BagResponse> bagInfoPage = bagService.getAll(pageable);
        return ResponseEntity.ok(bagInfoPage);
    }

    @Operation(summary = "Create a new bag")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<BagResponse> add(@RequestBody BagRequestDto bagRequestDto) {
        BagResponse newBag = bagService.add(bagRequestDto);
        return ResponseEntity.ok(newBag);
    }

    @Operation(summary = "Soft delete a bag by updating its status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id, @RequestBody LocalDateTime localDateTime) {
        bagService.softDelete(id, localDateTime);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update bag details")
    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody BagRequestDto bagRequestDto, @PathVariable Integer id) {
        bagService.update(bagRequestDto, id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Activate a bag by setting its status to active")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public ResponseEntity<Void> activateProduct(@PathVariable Integer id, @RequestBody LocalDateTime createdAt) {
        bagService.activateProduct(id, createdAt);
        return ResponseEntity.ok().build();
    }
}