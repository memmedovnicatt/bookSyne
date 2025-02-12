package com.example.booksyne.service;

import com.example.booksyne.dao.entity.Payment;
import com.example.booksyne.model.dto.request.CardRequestDto;
import com.example.booksyne.model.dto.request.CreatePaymentDto;
import com.example.booksyne.model.dto.request.PaymentDto;
import com.example.booksyne.model.dto.response.CreatePaymentResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    void pay(PaymentDto paymentDto, HttpServletRequest httpServletRequest);
}