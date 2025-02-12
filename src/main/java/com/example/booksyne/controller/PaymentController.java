package com.example.booksyne.controller;

import com.example.booksyne.dao.entity.Payment;
import com.example.booksyne.model.dto.request.CardRequestDto;
import com.example.booksyne.model.dto.request.CreatePaymentDto;
import com.example.booksyne.model.dto.request.PaymentDto;
import com.example.booksyne.model.dto.response.CreatePaymentResponse;
import com.example.booksyne.service.CardService;
import com.example.booksyne.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "Perform payment using the provided payment details")
    @PostMapping()
    public ResponseEntity<Double> pay(@RequestBody PaymentDto paymentDto, HttpServletRequest httpServletRequest) {
        paymentService.pay(paymentDto, httpServletRequest);
        return ResponseEntity.ok().build();
    }
}