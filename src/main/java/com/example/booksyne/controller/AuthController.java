package com.example.booksyne.controller;


import com.example.booksyne.model.dto.request.ChangePasswordDto;
import com.example.booksyne.model.dto.request.LoginRequest;
import com.example.booksyne.model.dto.request.RecoveryPassword;
import com.example.booksyne.model.dto.request.UserRegisterDto;
import com.example.booksyne.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "User login")
    @PostMapping("/sign-in")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @Operation(summary = "Registers a new user by providing required user details")
    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        authService.register(userRegisterDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Request password reset", description = "Sends a password reset token to the user's email for recovery.")
    @PatchMapping("/forgot-password")
    public ResponseEntity<Void> requestPasswordReset(@RequestParam @NotBlank @Email String email) {
        authService.requestPasswordReset(email);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Reset password", description = "Allows the user to reset their password by providing the recovery token and a new password.")
    @PatchMapping("/recovery-password")
    public ResponseEntity<String> resetPassword(@RequestBody @Valid RecoveryPassword recoveryPassword) {
        return authService.resetPassword(recoveryPassword);
    }
}