package com.example.booksyne.controller;

import com.example.booksyne.model.dto.request.ProductAddDto;
import com.example.booksyne.service.ProductTypeEntityService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/productEntities")
@RestController
@RequiredArgsConstructor
public class ProductTypeEntityController {
    private final ProductTypeEntityService productTypeEntityService;

    @Operation(summary = "New product add to stock together method and service names")
    @PostMapping()
    public ResponseEntity<Void> addProduct(@RequestBody ProductAddDto productAddDto) {
        productTypeEntityService.addProduct(productAddDto);
        return ResponseEntity.ok().build();
    }
}
