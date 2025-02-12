package com.example.booksyne.service.impl;

import com.example.booksyne.model.dto.request.EmailRequest;
import com.example.booksyne.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {
    private final JavaMailSender javaMailSender;

    @Override
    public void sendSimpleEmail(EmailRequest emailRequest) {
        log.info("sendSimpleEmail was started");
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailRequest.getReceiver());
        String myEmail = "nicatmmdv11@gmail.com";
        msg.setFrom(myEmail);
        msg.setSubject(emailRequest.getSubject());
        msg.setText(emailRequest.getText());
        javaMailSender.send(msg);
        log.info("Successfully sent email to {}", emailRequest.getReceiver());
    }
}