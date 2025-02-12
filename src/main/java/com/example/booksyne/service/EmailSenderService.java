package com.example.booksyne.service;

import com.example.booksyne.model.dto.request.EmailRequest;
import org.springframework.stereotype.Service;

@Service
public interface EmailSenderService {
    void sendSimpleEmail(EmailRequest emailRequest);
}