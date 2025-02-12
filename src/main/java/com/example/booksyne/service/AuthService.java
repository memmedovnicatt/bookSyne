package com.example.booksyne.service;

import com.example.booksyne.model.dto.request.ChangePasswordDto;
import com.example.booksyne.model.dto.request.LoginRequest;
import com.example.booksyne.model.dto.request.RecoveryPassword;
import com.example.booksyne.model.dto.request.UserRegisterDto;
import com.example.booksyne.model.dto.response.LoginResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    void register(UserRegisterDto userRegisterDto);

    ResponseEntity<?> login(LoginRequest loginRequest);

    ResponseEntity<String> requestPasswordReset(String email);

    ResponseEntity<String> resetPassword(RecoveryPassword recoveryPassword);



}
