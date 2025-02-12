package com.example.booksyne.controller;

import com.example.booksyne.model.dto.request.SupplierRequestDto;
import com.example.booksyne.model.dto.request.SupplierRequestDtoForUpdate;
import com.example.booksyne.model.dto.response.SupplierDetailResponse;
import com.example.booksyne.model.dto.response.SupplierResponseDto;
import com.example.booksyne.model.dto.response.SupplierResponseForGetAll;
import com.example.booksyne.service.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @Operation(summary = "Adding a new supplier")
    @PostMapping()
    private ResponseEntity<SupplierResponseDto> add(@RequestBody SupplierRequestDto supplierRequestDto) {
        SupplierResponseDto supplierResponseDto = supplierService.add(supplierRequestDto);
        return ResponseEntity.ok(supplierResponseDto);
    }

    @Operation(summary = "Delete a supplier by its ID")
    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Integer id) {
        supplierService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get details of a book by its ID")
    @GetMapping("/{id}")
    private ResponseEntity<SupplierDetailResponse> getById(@PathVariable Integer id) {
        SupplierDetailResponse supplierDetailResponse = supplierService.getById(id);
        return ResponseEntity.ok(supplierDetailResponse);
    }

    @Operation(summary = "Get a list of supplier")
    @GetMapping()
    private ResponseEntity<List<SupplierResponseForGetAll>> getAll() {
        List<SupplierResponseForGetAll> supplierResponseForGetAll = supplierService.getAll();
        return ResponseEntity.ok(supplierResponseForGetAll);
    }

    @Operation(summary = "Update selected fields of a supplier by ID")
    @PatchMapping("/{id}")
    private ResponseEntity<Void> update(@RequestBody SupplierRequestDtoForUpdate supplierRequestDtoForUpdate, @PathVariable Integer id) {
        supplierService.update(supplierRequestDtoForUpdate,id);
        return ResponseEntity.ok().build();
    }
}