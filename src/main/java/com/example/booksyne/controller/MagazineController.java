package com.example.booksyne.controller;

import com.example.booksyne.dao.entity.Magazine;
import com.example.booksyne.model.dto.request.MagazineDtoForUpdate;
import com.example.booksyne.model.dto.request.MagazineRequestDto;
import com.example.booksyne.model.dto.response.MagazineInfoResponse;
import com.example.booksyne.model.dto.response.MagazineResponse;
import com.example.booksyne.model.dto.response.MagazineResponseForGetAll;
import com.example.booksyne.service.MagazineService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequestMapping("/magazines")
@RestController
@RequiredArgsConstructor
public class MagazineController {
    private final MagazineService magazineService;

    @Operation(summary = "Add a new magazine")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<MagazineResponse> add(@RequestBody MagazineRequestDto magazineRequestDto) {
        MagazineResponse magazineResponse = magazineService.add(magazineRequestDto);
        return ResponseEntity.ok(magazineResponse);
    }

    @Operation(summary = "Delete a magazine by ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id, @RequestBody LocalDateTime localDateTime) {
        magazineService.softDelete(id, localDateTime);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Activate a magazine by ID")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public ResponseEntity<Void> activateProduct(@PathVariable Integer id, @RequestBody LocalDateTime createdAt) {
        magazineService.activateProduct(id, createdAt);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get all magazines with pagination")
    @GetMapping()
    public ResponseEntity<Page<MagazineResponseForGetAll>> getAll(Pageable pageable) {
        Page<MagazineResponseForGetAll> magazineResponseForGetAll = magazineService.getAll(pageable);
        return ResponseEntity.ok(magazineResponseForGetAll);
    }


    @Operation(summary = "Get magazine details by ID")
    @GetMapping("/{id}")
    public ResponseEntity<MagazineInfoResponse> getById(@PathVariable Integer id) {
        MagazineInfoResponse magazineInfoResponse = magazineService.getById(id);
        return ResponseEntity.ok(magazineInfoResponse);
    }

    @Operation(summary = "Update an existing magazine by ID")
    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody MagazineDtoForUpdate magazineDtoForUpdate, @PathVariable Integer id) {
        magazineService.update(magazineDtoForUpdate, id);
        return ResponseEntity.ok().build();
    }
}