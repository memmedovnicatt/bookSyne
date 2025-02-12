package com.example.booksyne.controller;

import com.example.booksyne.dao.entity.Card;
import com.example.booksyne.dao.repository.UserRepository;
import com.example.booksyne.model.dto.request.CardRequestDto;
import com.example.booksyne.model.dto.request.ChangePasswordDto;
import com.example.booksyne.model.dto.response.NewUserInformation;
import com.example.booksyne.model.dto.response.UserProfileResponse;
import com.example.booksyne.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Show profile for user")
    @GetMapping("/{username}")
    public ResponseEntity<UserProfileResponse> showProfile(@PathVariable String username) {
        UserProfileResponse userProfileResponse = userService.showProfile(username);
        return ResponseEntity.ok(userProfileResponse);
    }

    @Operation(summary = "Change the password of the authenticated user")
    @PatchMapping("/change-password")
    public ResponseEntity<String> changePassword(HttpServletRequest request, @RequestBody @Valid ChangePasswordDto changePasswordDto) {
        return userService.changePassword(request, changePasswordDto);
    }
}